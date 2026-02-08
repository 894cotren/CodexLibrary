package com.gery.dcc.controller;


import com.gery.dcc.domain.GreysDCCConfig;
import com.gery.dcc.model.Response;
import com.gery.dcc.model.ResponseCode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.web.bind.annotation.*;

/**
 * @author grey
 * @description 动态配置管理
 */
@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/grey/dcc/")
public class DCCController{

    @Resource
    private RTopic dccTopic;

    @Resource
    private GreysDCCConfig greysDCCConfig;

    /**
     * 动态值变更
     * 流程大概是这样的：
     * 1. 这边接口调用redis发布订阅，触发topic
     * 2. topic配置了监听器，监听方法内，为redis中的动态配置刷新最新值，然后再更新对象内的属性值。
     * 这样就完成了动态配置。
     */
    @RequestMapping(value = "update_config", method = RequestMethod.GET)
    public Response<Boolean> updateConfig(@RequestParam String key, @RequestParam String value) {
        try {
            log.info("DCC 动态配置值变更 key:{} value:{}", key, value);
            dccTopic.publish(key + "," + value);
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("DCC 动态配置值变更失败 key:{} value:{}", key, value, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @RequestMapping(value = "get_config", method = RequestMethod.GET)
    public Response<String> updateConfig() {
        String name = greysDCCConfig.getName();
        String part = greysDCCConfig.getPart();
        return Response.<String>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .data("---配置结果--- name:" + name + " part:" + part)
                .build();
    }

}
