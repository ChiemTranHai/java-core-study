package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TestAsync {

    @Autowired
    @Qualifier("taskExecutor")
    private Executor executor;

    @Async
    public void testAsync() throws InterruptedException {
        ThreadPoolTaskExecutor taskExecutor=(ThreadPoolTaskExecutor) executor;
        int size = taskExecutor.getQueueSize();
        System.out.println(Thread.currentThread().getName() + " queue " + size);
        Thread.sleep(30000);
    }
}
