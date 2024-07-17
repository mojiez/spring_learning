package com.atyichen.testmyspring;

import org.junit.Test;
import org.myspringframework.core.ApplicationContext;
import org.myspringframework.core.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class OrderServiceTest {
    @Test
    public void myspringTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myspring-test.xml");
        Object vip = applicationContext.getBean("vip");
        System.out.println(vip);
        Object orderService = applicationContext.getBean("orderService");
        OrderService service = (OrderService) orderService;
        service.generate();
    }
}