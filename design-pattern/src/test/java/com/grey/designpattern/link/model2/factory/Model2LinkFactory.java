package com.grey.designpattern.link.model2.factory;

import com.grey.designpattern.link.model2.LinkArmory;
import com.grey.designpattern.link.model2.chain.BusinessLinkedList;
import com.grey.designpattern.link.model2.logicnode.LogicNode1;
import com.grey.designpattern.link.model2.logicnode.LogicNode2;
import com.grey.designpattern.link.model2.response.LogicResponse;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author grey
 * 工厂组装配置
 */
@Service
public class Model2LinkFactory {


    @Bean("link001")
    public BusinessLinkedList<String, DynamicContext, LogicResponse> link001(LogicNode1 logicNode1,LogicNode2 logicNode2) {
        LinkArmory<String, DynamicContext, LogicResponse> linkArmory = new LinkArmory<>("link001", logicNode1, logicNode2);
        return linkArmory.getLogicLink();
    }

    @Bean("link002")
    public BusinessLinkedList<String, DynamicContext, LogicResponse> link002(LogicNode1 logicNode1,LogicNode2 logicNode2) {
        LinkArmory<String, DynamicContext, LogicResponse> linkArmory = new LinkArmory<>("link002", logicNode2,logicNode1);
        return linkArmory.getLogicLink();
    }



    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }
}
