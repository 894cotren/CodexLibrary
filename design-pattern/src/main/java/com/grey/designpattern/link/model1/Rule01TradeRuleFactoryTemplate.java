package com.grey.designpattern.link.model1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 责任链装配工厂，一个工厂负责一个责任链，定义自己的上下文
 */
@Service
public class Rule01TradeRuleFactoryTemplate {

    //这里可以注入自定义结点

    public ILogicLink<String, Rule01TradeRuleFactoryTemplate.DynamicContext, String> openLogicLink() {
        // 这里实现装配
        return null;
    }

    // 这里是上下文类
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }

}
