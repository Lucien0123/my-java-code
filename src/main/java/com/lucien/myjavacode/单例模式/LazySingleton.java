package com.lucien.javacode.单例模式;

import sun.jvm.hotspot.debugger.cdbg.basic.LazyBlockSym;

/**
 * Created by lucien on 2018/2/23.
 * 懒汉式单例：在需要的时候创建
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){
        System.out.println("lazy singleton init...");
    }

    /* 在多线程的环境中，可能存在多个线程同时获取单例对象，此事可以多个线程同时操作getInstance()
     * 导致产生多个实例，所以需要为该方法增加同步代码控制 */
    public static LazySingleton getInstance(){
        if (instance == null)
            instance = new LazySingleton();
        return instance;
    }
}
