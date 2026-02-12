package com.grey.designpattern.link.model1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author grey
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkModel1Test {

    @Autowired
    private Rule01TradeRuleFactory rule01TradeRuleFactory;

    @Test
    public void test01() throws Exception {
        ILogicLink<String, Rule01TradeRuleFactory.DynamicContext, String> headNode = rule01TradeRuleFactory.openLogicLink();
        String result =   headNode.apply("test", new Rule01TradeRuleFactory.DynamicContext());
        System.out.println(result);
    }
}
