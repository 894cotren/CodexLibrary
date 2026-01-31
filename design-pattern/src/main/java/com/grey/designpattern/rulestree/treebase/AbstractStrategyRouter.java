package com.grey.designpattern.rulestree.treebase;

import lombok.Getter;
import lombok.Setter;

/**
 *  策略路由抽象类，整合两个接口的方法执行的流程。（当然如果需要还可以自己加，比如加个多线程查询。就像AbstractMultiThreadStrategyRouter）
 *  虽然这个叫策略路由抽象类，
 *  但是这个类只是实现了一个通用的调用子类结点提供的下一个结点的apply方法。
 *  所以这个类虽然叫抽象策略路由类，
 *  但是具体的路由规则是子类的get方法所实现的，（根据不同的规则逻辑返回不同的下一个结点）。
 *
 *  比如在apply里面执行逻辑获取到了不同的结果放入上下文D类
 *  然后get方法实现根据D类的不同的结果，返回对应的下一个结点的逻辑。
 *  这样在apply最后调用router路由方法，就整个逻辑串起来了。
 *
 *  所以当前类的router只是一个固定的执行方法，不是做决策的。
 *  路由决策的实现是子类的apply方法+上下文D+get方法 一起实现的。
 */
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyMapper<T, D, R>, StrategyHandler<T, D, R> {

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    /**
     * 获取子类提供的下一个结点并执行的一套通用逻辑
     *
     */
    public R router(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandler<T, D, R> strategyHandler = this.get(requestParameter, dynamicContext);
        if(null != strategyHandler) return strategyHandler.apply(requestParameter, dynamicContext);
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }

}
