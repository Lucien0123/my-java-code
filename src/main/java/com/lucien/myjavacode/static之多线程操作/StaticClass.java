package com.lucien.myjavacode.static之多线程操作;

public class StaticClass {

    public static String str = "static String";

    public static void setVal(String val) {
        str = val;
    }

    public static String getVal() {
        return str;
    }
}
