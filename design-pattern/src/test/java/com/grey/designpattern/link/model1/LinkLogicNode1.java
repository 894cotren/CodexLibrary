package com.grey.designpattern.link.model1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author grey
 */
@Service
@Slf4j
public class LinkLogicNode1 extends AbstractLogicLink<String, Rule01TradeRuleFactory.DynamicContext, String> {
    @Override
    public String apply(String requestParameter, Rule01TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("LinkLogicNode1 apply");
        //执行下一个
        return invokeNext(requestParameter, dynamicContext);
    }
}
