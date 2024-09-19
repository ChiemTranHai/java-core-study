package com.example.demo.study;

import com.example.demo.TestAsync;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class TestThreadPool {

    private static ApplicationContext context = null;

    public TestThreadPool(ApplicationContext context) {
        this.context = context;
    }

    static Integer counter = 0;

    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void testThreadPool() throws InterruptedException {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = context.getBean(ThreadPoolTaskExecutor.class);
        System.out.println(threadPoolTaskExecutor.getPoolSize());
        Runnable runnable = () -> {
            IntStream.range(0, 1250).forEach(TestThreadPool::increaseCounter);
            System.out.println("run ne " + Thread.currentThread().getName() + " queue " + threadPoolTaskExecutor.getQueueSize());
        };
        TestAsync testAsync = context.getBean(TestAsync.class);
        IntStream.range(0, 100).parallel().forEach((i) -> {
            try {
                testAsync.testAsync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(runnable);
        t2.start();

        t2.join();
        System.out.println(counter);
    }

    private static void increaseCounter(int i) {
        reentrantLock.lock();
        counter++;
        reentrantLock.unlock();
    }
}
