package com.atyichen.project;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class SomeServiceTest {

    @Test
    public void testSomeService() {
        // 不使用反射机制调用方法
        SomeService someService = new SomeService();
        someService.doSome();
        System.out.println(someService.doSome("张三"));
        System.out.println(someService.doSome("李四",250));
    }

    @Test
    public void testDoSomeWithReflect() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 获取类
        Class<?> clazz = Class.forName("com.atyichen.project.SomeService");

        // 获取方法
        Method doSome = clazz.getDeclaredMethod("doSome", String.class, int.class);

        // 调用方法
        Constructor<?> con = clazz.getDeclaredConstructor();
        Object obj = con.newInstance();

        Object returnValue = doSome.invoke(obj, "李四", 250);
        System.out.println(returnValue);
    }

}