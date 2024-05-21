package com.atyichen.client;

import com.atyichen.web.UserAction;

public class Test {
    /**
     * 控制反转
     * 反转两件事：
     * 1. 我不在程序中采用硬编码的方式来new对象了（new对象我不管了，new对象的权利交出去了）
     * 2. 我不在程序中采用硬编码的方式来维护对象的关系了，（对象之间关系的维护权，我也不管了，交出去了）
     * Spring框架实现了控制反转Ioc这种思想
     * Spring是实现了Ioc思想的容器
     *
     * 控制反转是思想 依赖注入是其中一种实现Ioc的方法
     * 依赖注入 1.set注入（set方法给属性赋值） 2.构造方法注入 （执行构造方法给属性赋值）
     * @param args
     */
    public static void main(String[] args) {
        UserAction userAction = new UserAction();
        userAction.deleteRequest();
    }
}
