package com.lucien.javacode.单例模式;

/**
 * Created by lucien on 2018/2/23.
 */
public class LazySyncSingleton {

    private static LazySyncSingleton instance;

    private LazySyncSingleton(){
        System.out.println("Synchronized Lazy init...");
    }

    /* 为getInstance()添加synchronized同步控制， */
    public static synchronized LazySyncSingleton getInstance(){
        if (instance == null)
            instance = new LazySyncSingleton();
        return instance;
    }
}
