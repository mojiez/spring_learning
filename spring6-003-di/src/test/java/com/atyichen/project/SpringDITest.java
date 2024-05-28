package com.atyichen.project;

import com.atyichen.project.bean.User;
import com.atyichen.project.service.CustomerService;
import com.atyichen.project.service.OrderService;
import com.atyichen.project.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {
    @Test
    public void testConsDI() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        CustomerService csBean = applicationContext.getBean("csBean", CustomerService.class);
        csBean.save();
    }
    @Test
    public void testSetDI() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userServiceBean = applicationContext.getBean("userServiceBean", UserService.class);
        userServiceBean.saveUser();
    }
    @Test
    public void testSetDI2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("setDI.xml");
        OrderService orderServiceBean = applicationContext.getBean("orderServiceBean2", OrderService.class);
        orderServiceBean.generate();
    }
    @Test
    public void testSimpleType() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("setDI.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }
}
