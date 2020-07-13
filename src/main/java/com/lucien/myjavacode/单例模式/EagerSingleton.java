package com.lucien.javacode.单例模式;

/**
 * Created by lucien on 2018/2/23.
 * 饿汉式，在类初始化时即生成了实例，并没有起到懒加载的效果
 */
public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("Eager Singleton init...");
    }

    private static EagerSingleton getInstance() {
        return instance;
    }


}
