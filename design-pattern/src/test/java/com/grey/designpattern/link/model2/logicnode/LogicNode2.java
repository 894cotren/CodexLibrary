package com.grey.designpattern.link.model2.logicnode;

import com.grey.designpattern.link.model2.factory.Model2LinkFactory;
import com.grey.designpattern.link.model2.handler.ILogicHandler;
import com.grey.designpattern.link.model2.response.LogicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author grey
 */
@Service
@Slf4j
public class LogicNode2 implements ILogicHandler<String, Model2LinkFactory.DynamicContext, LogicResponse> {
    @Override
    public LogicResponse apply(String requestParameter, Model2LinkFactory.DynamicContext dynamicContext) throws Exception {
        log.info("LogicNode2 apply");
        return null;
    }
}
