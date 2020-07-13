package com.lucien.myjavacode.Java细粒度锁;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lucien on 2018/1/29.
 */
public class ConcurrentCollection {

    /* 先进后出，继承自Vector，通过Synchronized同步锁实现线程安全 */
    Stack<String> strings = new Stack<>();

    /* Hashtable 对比 HashMap增加了Synchronized实现线程安全 */
    Hashtable<String, String> stringHashtable = new Hashtable<>();
    HashMap<String, String> stringHashMap = new HashMap<>();



    public static void main(String[] args) {
        ConcurrentHashMap<String, String> stringConcurrentHashMap = new ConcurrentHashMap<>();
        List<String> lista = new LinkedList<>();
        stringConcurrentHashMap.put("123", "123");
    }
}
