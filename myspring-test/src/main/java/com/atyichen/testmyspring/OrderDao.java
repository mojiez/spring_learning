package com.atyichen.testmyspring;

public class OrderDao {
    public void insert() {
        System.out.println("mispring test 正在保存订单信息");
    }

    @Override
    public String toString() {
        return "OrderDao{}";
    }
}
