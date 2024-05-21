package com.atyichen.dao.impl;

import com.atyichen.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void deleteById() {
        System.out.println("正在删除用户信息");
    }
}
