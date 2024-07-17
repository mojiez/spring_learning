package com.atyichen.testmyspring;

public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public void generate() {
        System.out.println("service 在调用");
        orderDao.insert();
    }

    @Override
    public String toString() {
        return "OrderService{" +
                "orderDao=" + orderDao +
                '}';
    }
}
