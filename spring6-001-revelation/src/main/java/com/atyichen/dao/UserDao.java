package com.atyichen.dao;

/**
 * 持久层
 * 专门处理增删改查的
 * @author mojie
 */
public interface UserDao {
    /**
     * 根据id删除用户信息
     */
    void deleteById();
}
