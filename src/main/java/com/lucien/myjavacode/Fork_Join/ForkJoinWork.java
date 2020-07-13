package com.lucien.myjavacode.Fork_Join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 任务描述：求1～1000000的和
 */
public class ForkJoinWork extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private static final Long CRITICAL = 100000000000L;  //粒度，临界值

    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        //判断是否小于拆分粒度
        if ((end - start) <= CRITICAL) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 二分
            Long middle = start + (end - start) / 2;
            ForkJoinWork left = new ForkJoinWork(start, middle);
            ForkJoinWork right = new ForkJoinWork(middle + 1, end);
            left.fork();  // 拆分，压入线程栈
            right.fork(); // 拆分，压入线程栈

            // 合并
            Long result = left.join() + right.join();
            return result;
        }
    }
}

class ForkJoinWorkDemo {

    private static final Long END = 100000000000L;

    private static void test() throws InterruptedException, ExecutionException {
        //ForkJoin实现
        long l = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(); //实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Long> task = new ForkJoinWork(0L, END);//参数为起始值与结束值
        Long invoke = pool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("1 invoke = " + invoke + "  time: " + (l1 - l));
//        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }

    private static void test2() {
        // 不加线程
        Long x = 0L;
        Long y = END;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x += i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("2 invoke = " + x + "  time: " + (l1 - l));
    }

    private static void test3() {
        //Java 8 并行流的实现
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, END).parallel().reduce(0, Long::sum);
        long l1 = System.currentTimeMillis();
        System.out.println("3 invoke = " + reduce+"  time: " + (l1-l));
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        test2();
        test3();
    }
}
