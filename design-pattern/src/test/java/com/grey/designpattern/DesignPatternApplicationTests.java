package com.grey.designpattern;

import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternApplicationTests {

    @Resource
    DefaultActivityStrategyFactory defaultActivityStrategyFactory;

    /**
     * 标准规则树链路测试
     * @throws Exception
     */
    @Test
    void standardTest() throws Exception {
        defaultActivityStrategyFactory.strategyHandler().apply(new MarketProductEntity(),
                new DefaultActivityStrategyFactory.DynamicContext());
        System.out.println("-------------------执行结束");
    }

    @Test
    void contextLoads() {
    }



}
