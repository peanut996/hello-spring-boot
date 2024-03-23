package com.example.hellospringboot.unit;


import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ConcurrentTest {


    @Test
    void synchronize() throws InterruptedException {
        Counter counter = new Counter();

        // 创建并启动两个线程，一个用于增加计数，一个用于减少计数
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 1; i++) {
                counter.increment();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 1; i++) {
                counter.decrement();
            }
        });

        // 启动线程
        incrementThread.start();
        Thread.sleep(100);
        decrementThread.start();

        // 等待两个线程执行完成
        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出最终的计数值
        System.out.println("Final count is: " + counter.count);
    }


    @Test
    void ThreadLocal() {
        ThreadLocal<Counter> t = new ThreadLocal<>();
        t.set(new Counter());
    }

    @Test
    void CountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        countDownLatch.countDown();
    }

    @Test
    void CyclicBarrier() throws InterruptedException {
        // 请求的数量
        final int threadCount = 10;
        // 需要同步的线程数量
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(() -> {
                try {
                    CyclicBarrierHelper(cyclicBarrier, threadNum);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    void CyclicBarrierHelper(CyclicBarrier cyclicBarrier, int threadnum) throws InterruptedException, BrokenBarrierException {
        System.out.println("threadnum:" + threadnum + " is ready");
        try {
            /**等待60秒，保证子线程完全执行结束*/
            cyclicBarrier.await(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("-----CyclicBarrierException------");
        }
        System.out.println("threadnum:" + threadnum + " is finish");
    }

    @Test
    void Semaphore() throws InterruptedException {
        final int N = 7;
        Semaphore s = new Semaphore(3);
        for(int i = 0; i < N; i++) {
            new Worker(s, i).start();
        }

        Thread.sleep(5000);
    }
}


class Counter {
    public int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            System.out.println("increment 成功获取对象锁");
            count++;
            if (count < 5) {
                lock.notifyAll(); // 唤醒所有等待的线程
            } else {
                lock.notify(); // 唤醒一个等待的线程
            }
            try {
                System.out.println("释放对象并等待");
                lock.wait(); // 当前线程等待
                System.out.println("结束等待");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 重新设置中断状态
            }
        }
    }

    public void decrement() {
        synchronized (lock) {
            System.out.println("decrement 成功获取对象锁");
            count--;
            lock.notifyAll();
        }
    }
}


class Worker extends Thread {
    private Semaphore s;
    private int num;
    public Worker(Semaphore s, int num) {
        this.s = s;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            s.acquire();
            System.out.println("worker" + num +  " using the machine");
            Thread.sleep(1000);
            System.out.println("worker" + num +  " finished the task");
            s.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
