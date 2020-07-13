package com.lucien.myjavacode.static之多线程操作;

public class TestClass {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println("内存地址：" + System.identityHashCode(StaticClass.str));
            System.out.println("值：" + StaticClass.str);
            String threadStr1 = StaticClass.str;
            System.out.println("内存地址111：" + System.identityHashCode(threadStr1));
            threadStr1 += "thread1";
            System.out.println(threadStr1);
            StaticClass.setVal(threadStr1);
            System.out.println("static：" + StaticClass.str);
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("------------------");
            System.out.println("内存地址：" + System.identityHashCode(StaticClass.str));
            System.out.println("2值：" + StaticClass.str);
            String threadStr2 = StaticClass.str;
            threadStr2 += "thread2";
            System.out.println("内存地址222：" + System.identityHashCode(threadStr2));
            System.out.println(threadStr2);
            StaticClass.setVal(threadStr2);
            System.out.println("2static：" + StaticClass.str);

        });

        thread.start();
        thread2.start();

        System.out.println(StaticClass.str);
    }
}
