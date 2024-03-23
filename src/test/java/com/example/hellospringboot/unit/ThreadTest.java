package com.example.hellospringboot.unit;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadTest {
    private static void print(ExecutorService t) {
        for (int i = 0; i < 10; i++) {
            final int n = i;
            t.submit(() -> System.out.println(n));
        }
    }

    @Test
    void executor() {
        ThreadPoolExecutor t = new ThreadPoolExecutor(20, 30, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        print(t);
    }


    @Test
    void threadPool() throws InterruptedException {
        // 采用无界阻塞队列，不会拒绝任何任务，适合CPU密集型任务
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(9);

        // 采用无界阻塞队列，但是线程只有1，适合串行执行的任务
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 可以无限申请线程，但是如果消费者速度慢于生产者，容易创建大量线程导致OOM
        // 适用场景：用于并发执行大量短期的小任务
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        print(cachedThreadPool);

        // 定时任务的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int n = i;
            scheduledExecutorService.schedule(() -> System.out.println(n), i, TimeUnit.SECONDS);
        }
        scheduledExecutorService.shutdown();

        boolean res = scheduledExecutorService.awaitTermination(15, TimeUnit.SECONDS);

        System.out.println(res);

    }

}

