package com.atyichen.project.service;

import com.atyichen.project.dao.OrderDao;

public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void generate() {
        orderDao.generate();
    }
}
