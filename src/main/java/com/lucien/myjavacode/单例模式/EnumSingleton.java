package com.lucien.javacode.单例模式;

/**
 * Created by lucien on 2018/2/23.
 * Effective Java作者Josh Bloch 提倡的方式
 * 1、自由序列化
 * 2、保证只有一个实例
 * 3、线程安全
 */
enum EnumSingleton {
    INSTANCE;

    public void commonFunction(){
        System.out.println("模拟普通方法的执行！！！");
    }
}

class SingletonTest {

    public static void main(String[] args) {
       EnumSingleton.INSTANCE.commonFunction();
    }
}
