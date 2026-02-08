package com.gery.dcc.domain;

import com.gery.dcc.domain.annotaion.DCCValue;
import com.gery.dcc.redis.IRedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author grey
 * 这个是一个DCC动态配置中心的一个初始化作业的工厂
 * 用于给动态配置的对象赋值初始值，并且收集该成员属性的反射对象，用于后续动态赋值处理
 */
@Slf4j
@Configuration
public class DCCValueBeanFactory implements BeanPostProcessor {

    /**
     * redis缓存key前缀 ，也是dccObjGroup的key前缀。 后面都拼接变量名
     */
    private static final String BASE_CONFIG_PATH = "dcc_demo_test_";

    private final Map<String, Object> dccObjGroup = new HashMap<>();


    @Resource
    private RedissonClient redissonClient;


    /**
     * 配置redisson 的发布订阅，注册监听器
     * @param redissonClient
     * @return
     */
    @Bean("doTopic")
    public RTopic testRedisTopicListener(RedissonClient redissonClient){
        RTopic topic = redissonClient.getTopic("dcc_demo_test");
        //注册redisson的监听器
        topic.addListener(String.class, (charSequence, s) -> {
            String[] split = s.split(",");

            // 获取值
            String attribute = split[0];
            String key = BASE_CONFIG_PATH + attribute;
            String value = split[1];

            // 设置值到redis中
            RBucket<String> bucket = redissonClient.getBucket(key);
            boolean exists = bucket.isExists();
            if (!exists) return;
            bucket.set(value);

            // 获取到对应的配置对象
            Object objBean = dccObjGroup.get(key);
            if (null == objBean) return;

            Class<?> objBeanClass = objBean.getClass();
            // 检查 objBean 是否是代理对象
            if (AopUtils.isAopProxy(objBean)) {
                // 获取代理对象的目标对象
                objBeanClass = AopUtils.getTargetClass(objBean);
            }

            try {
                // 1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
                // 2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
                Field field = objBeanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);

                log.info("DCC 节点监听，动态设置值 {} {}", key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return topic;
    }

    /**
     * 在bean生命周期的这个阶段，利用对外提供的接口BeanPostProcessor实现bean的注解处理
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 注意；增加 AOP 代理后，获得类的方式要通过 AopProxyUtils.getTargetClass(bean);
        // 不能直接 bean.class 因为代理后类的结构发生变化，这样不能获得到自己的自定义注解了。

        //获取bean的class对象
        Class<?> targetBeanClass = bean.getClass();
        //判断是否是代理对象，获取到bean的真正的class对象和真正的bean对象（未被代理的class、bean）
        Object targetBeanObject = bean;
        if (AopUtils.isAopProxy(bean)){
            targetBeanClass = AopUtils.getTargetClass(bean); //获取到真正的class对象
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean); //获取到真正的bean对象
        }
        //获取到该class对象的fields 成员变量对象集合
        Field[] fields = targetBeanClass.getDeclaredFields();
        //判断成员变量是否有注解 @DCCValue
        for (Field field : fields) {
            // 如果没有该注解不做处理
            if (!field.isAnnotationPresent(DCCValue.class)) {
                continue;
            }
            // 有注解则获取注解的值，并注入到成员变量中，并将该 bean 收集到自定义容器中管理
            DCCValue dccValue = field.getAnnotation(DCCValue.class);
            String value = dccValue.value();
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException(field.getName() + " @DCCValue is not config value config case 「isSwitch/isSwitch:1」");
            }
            //拆分value结构（变量名:值）,拼接redis缓存中配置的key，以及拿到默认值；
            String[] splits = value.split(":");
            String key = BASE_CONFIG_PATH.concat(splits[0]);
            String defaultValue = splits.length == 2 ? splits[1] : null;
            // 设置初始值
            String setValue = defaultValue;

            //为该成员变量设置初始值
            try {
                // 如果初始值为空则抛出异常
                if (StringUtils.isBlank(defaultValue)) {
                    throw new RuntimeException("dcc config error " + key + " is not null - 请配置默认值！");
                }

                // 从redis中尝试拉取该配置的信息
                RBucket<String> bucket = redissonClient.getBucket(key);
                boolean exists = bucket.isExists();
                if (exists) {
                // 如果不为空，使用redis里的配置
                    setValue = bucket.get();
                }else {
                // 如果为空，把当前初始值设置上去
                    bucket.set(setValue);
                }
                field.setAccessible(true);
                field.set(targetBeanObject, setValue);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            //收集该bean的原始对象实例；（这里不收集反射对象是因为，反射对象只是蓝图啊，到时候只有它的话，那么修改谁呢？
            // 反射对象不能反向找目标对象，目标对象到时可以找到该反射对象） 所以就是这么一回事
            // 另外的点：一个bean中很可能集中多个该动态的配置存bean可以同时都处理。（但是主要的点还是上面的，没bean实例你赋值不了，拔剑四顾心茫然）
            // 这里的key 是存储的具体到某个变量的key，后续跟新也好找。一个变量映射一个obj
            dccObjGroup.put(key, targetBeanObject);
        }
        //返回bean
        return bean;
    }
}
