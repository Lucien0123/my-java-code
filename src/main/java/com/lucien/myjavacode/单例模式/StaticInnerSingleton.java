package com.lucien.javacode.单例模式;

/**
 * Created by lucien on 2018/2/23.
 */
public class StaticInnerSingleton {

    private static class SingletonHodler{
        private static StaticInnerSingleton instance = new StaticInnerSingleton();
    }
    private StaticInnerSingleton(){
        System.out.println("static inner class singleton init...");
    }

    /* 只有在调用getInstance()的时候，才会初始化SingletonHolder内部类，生成instance实例，符合懒加载；
     * 同时，生产instance时，与饿汉式相似，所以也是线程安全的 */
    public static StaticInnerSingleton getInstance(){
        return SingletonHodler.instance;
    }
}
