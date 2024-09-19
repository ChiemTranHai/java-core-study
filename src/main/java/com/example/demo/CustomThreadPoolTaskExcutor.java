//package com.example.demo;
//
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.BlockingQueue;
//
//@Component
//public class CustomThreadPoolTaskExcutor extends ThreadPoolTaskExecutor {
//
//    private BlockingQueue<Runnable> blockingQueue;
//
//    @Override
//    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
//        blockingQueue = super.createQueue(queueCapacity);
//        return blockingQueue;
//    }
//
//    public int getQueueSize() {
//        return blockingQueue.size();
//    }
//}
