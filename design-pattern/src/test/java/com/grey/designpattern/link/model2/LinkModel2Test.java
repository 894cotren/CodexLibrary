package com.grey.designpattern.link.model2;

import com.grey.designpattern.link.model2.chain.BusinessLinkedList;
import com.grey.designpattern.link.model2.factory.Model2LinkFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author grey
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkModel2Test {

    @Resource(name = "link001")
    private BusinessLinkedList link001;

    @Resource(name = "link002")
    private BusinessLinkedList link002;

    @Test
    public void test01() throws Exception {
        link001.apply("test", new Model2LinkFactory.DynamicContext());
    }

    @Test
    public void test02() throws Exception {
        link002.apply("test", new Model2LinkFactory.DynamicContext());
    }
}
