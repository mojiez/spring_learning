package com.atyichen.project.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
    // Logger是一个日记记录器对象，用于记录不同级别的日志
    // 创建了一个与UserDao类相关联的日志记录器
    // 日志信息会带有该类的名称
    // 只有在 UserDao 类中调用这个 Logger 实例的代码会记录日志。如果在其他类中没有使用相同的 Logger 实例，则其他类的日志不会被记录到同一个地方，也不会显示为 UserDao 类的日志。
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    public void insert(){
        logger.info("数据库正在保存用户信息");
    }
}
