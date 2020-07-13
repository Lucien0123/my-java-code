package com.lucien.myjavacode.Java细粒度锁;

/**
 * Created by lucien on 2018/2/9.
 */
public class ThreadLocalTest {

    public static class MyRunnable implements Runnable {
        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();

        /* 范型的ThreadLocal */
        ThreadLocal<String> threadLocal = new ThreadLocal();
        threadLocal.set("aaa");
        threadLocal.set("bbb");
        threadLocal.set("ccc");
        String str_a = threadLocal.get();
        System.out.println(str_a);
    }
}
