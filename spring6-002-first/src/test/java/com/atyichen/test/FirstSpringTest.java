package com.atyichen.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringTest {
    @Test
    public void testSpring() {
        // 不是在调用getBean()方法的时候创建对象，执行以下代码的时候，就会实例化对象
        // 获取应用的上下文（Spring容器对象）
        // ApplicationContext接口下有很多实现类，其中有一个实现类叫做ClassPathXmlApplicationContext
        // ClassPathXmlApplicationContext是专门从类路径中加载spring配置文件的对象
        // 这行代码只要执行，就相当于启动了Spring容器，解析spring.xml文件，并且实例化所有的bean对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Test
    public void testLog4j2() {
        // 自己怎么使用log4j2记录信息

        // 创建日志记录器对象
        // 获取FirstSpringTest类的日志记录器对象，也就是说只要是FirstSpringTest类中的代码执行记录日志的话  就输出相关的日志信息
        Logger logger = LoggerFactory.getLogger("FirstSpringTest");

        // 记录日志
        logger.info("我是一条消息");
        logger.debug("我是一条调试信息");
        logger.error("我是一条错误信息");
    }
}
