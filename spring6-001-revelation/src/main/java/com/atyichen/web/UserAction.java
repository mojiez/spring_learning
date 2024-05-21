package com.atyichen.web;

import com.atyichen.service.UserService;
import com.atyichen.service.impl.UserServiceImpl;

/**
 * 持久层
 */
public class UserAction {
    /**
     * 这样做，违背了依赖倒转原则
     * 也就是上不能依赖下
     * 解决方法：面向接口编程
     * private UserService userService; 只有一个接口，不指明具体的实现类
     *
     */
    private UserService userService = new UserServiceImpl();

    /**
     * 删除用户信息的请求
     */
    public void deleteRequest() {
        userService.deleteUser();
    }
}
