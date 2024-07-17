package com.atyichen.project;

public class SomeService {
    public void doSome() {
        System.out.println("doSome方法执行");
    }
    public String doSome(String s){
        System.out.println("public String doSome(String s)执行");
        return s;
    }
    public String doSome(String s,int i) {
        System.out.println("public String doSome(String s,int i) 执行");
        return s+String.valueOf(i);
    }
}
