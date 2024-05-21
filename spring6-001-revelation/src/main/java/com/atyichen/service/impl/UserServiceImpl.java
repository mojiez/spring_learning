package com.atyichen.service.impl;

import com.atyichen.dao.UserDao;
import com.atyichen.dao.impl.UserDaoImpl;
import com.atyichen.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void deleteUser() {
        // 删除用户信息的业务逻辑
        // 调用持久层
        userDao.deleteById();
    }
}
