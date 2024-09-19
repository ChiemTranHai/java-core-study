package com.example.demo;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    private final CountDownLatch latch = new CountDownLatch(2);
    private int num;

    public void action1(){
        System.out.println("action 1"+ Thread.currentThread().getName());
        num=25;
        System.out.println("set num = "+num+" "+Thread.currentThread().getName());
        latch.countDown();
    }

    public void action2(){
        System.out.println("action 2"+ Thread.currentThread().getName());
        try {
            System.out.println("waiting for latch");
            latch.await();
            System.out.println("Completed");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wakeup");
        System.out.println("get num "+num+" "+Thread.currentThread().getName());
    }

    public void action3(){
        System.out.println("action 3 "+Thread.currentThread().getName());
        latch.countDown();
    }
}
