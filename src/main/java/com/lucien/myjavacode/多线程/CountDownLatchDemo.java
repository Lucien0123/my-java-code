package com.lucien.myjavacode.多线程;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 指定执行次数，当任务执行够一定次数后，停止
 */
public class CountDownLatchDemo {

    private static final Integer CORE_POOL_SIZE = 5;
    private static final Integer MAX_POOL_SIZE = 10;
    private static final Long KEEP_ALIVE_TIME = 60000L;
    private static final Integer WORK_QUEUE_LENGTH = 200;

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(WORK_QUEUE_LENGTH));

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        // 执行线程池任务
        executor.execute(new DemoTask("DelayEvent A", 2000, latch));
        executor.execute(new DemoTask("DelayEvent B", 2000, latch));
        executor.execute(new DemoTask("DelayEvent C", 2000, latch));
        executor.execute(new DemoTask("DelayEvent D", 2000, latch));
        executor.execute(new DemoTask("DelayEvent E", 2000, latch));
        executor.execute(new DemoTask("DelayEvent F", 2000, latch));
        executor.execute(new DemoTask("DelayEvent G", 2000, latch));
        executor.execute(new DemoTask("DelayEvent H", 2000, latch));
        executor.execute(new DemoTask("DelayEvent I", 2000, latch));
        executor.execute(new DemoTask("DelayEvent J", 2000, latch));
        try {
            latch.await();// 等待所有人任务结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束了...");
        executor.shutdown();
    }

    static class DemoTask implements Runnable {
        String statsName;
        int runTime;
        CountDownLatch latch;

        public DemoTask(String statsName, int runTime, CountDownLatch latch) {
            this.statsName = statsName;
            this.runTime = runTime;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(statsName + " ++start++ " + sdf.format(new Date()));
            try {
                Thread.sleep(runTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(statsName + " ++finish++ " + sdf.format(new Date()));
            latch.countDown();
        }
    }


}
