package com.atyichen.project.service;

import com.atyichen.project.dao.UserDao;
import com.atyichen.project.dao.VipDao;

public class UserService {
    private UserDao userDao;
    private VipDao vipDao;

    public void setVipDao(VipDao vipDao) {
        this.vipDao = vipDao;
    }

    // **开头 必须是 set**
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // 必须提供一个set方法
    // Spring容器会调用这个set方法，来给UserDao属性赋值
    public void saveUser() {
        userDao.insert();
        vipDao.insert();
    }
}
