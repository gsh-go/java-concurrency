package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample0 {
    public static int clientTotal = 50;
    public static int time = 10000;
    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal; i++) {
            SynchronizedExample0 example1 = new SynchronizedExample0();
            executorService.execute(() -> {
                try {
                    example1.add4();
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executorService.shutdown();
        Thread.sleep(1000);
        log.info("count:{}", count);
    }

    /**
     * synchronized修饰一个方法
     */
    private synchronized void add1() {
        for (int i = 0; i < time; i++)
            count++;
    }

    /**
     * synchronized修饰一个对象的代码块
     */
    private void add2() {
        synchronized (this) {
            for (int i = 0; i < time; i++)
                count++;
        }
    }

    /**
     * synchronized修饰static方法，即修饰整个类
     */
    private synchronized static void add3() {
        for (int i = 0; i < time; i++)
            count++;
    }

    /**
     * synchronized修饰一个类的代码块
     */
    private synchronized void add4() {
        synchronized (SynchronizedExample0.class) {
            for (int i = 0; i < time; i++)
                count++;
        }
    }
}
