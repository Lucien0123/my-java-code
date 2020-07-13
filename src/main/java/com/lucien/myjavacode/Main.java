package com.lucien.myjavacode;

/**
 * @Author huoershuai
 * @Date 2020/5/7
 */
public class Main {

    private static Object obj = new Object();

    public static void main(String[] args) {
        // 创建线程 thread
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "   begin wait...");
                synchronized (obj) {
                    obj.wait();
                }
                System.out.println(Thread.currentThread().getName() + "   end wait...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread1");

        // 创建线程 thread2
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "   begin wait...");
                synchronized (obj) {
                    obj.wait();
                }
                System.out.println(Thread.currentThread().getName() + "   end wait...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread2");



        // 启动
        thread1.start();
        thread2.start();

        try {
            // 睡眠一秒
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 如果调用 notify 的线程未获取 对象锁，在调用 notify 的时候会抛出 java.lang.IllegalMonitorStateException 异常
        synchronized (obj) {
            // 唤醒 使用 obj 调用 wait 方法的其中一个线程 (随机)
            obj.notify();
            // 唤醒 使用呢 obj 调用 wait 方法的所有线程
            obj.notifyAll();
        }
    }

}
