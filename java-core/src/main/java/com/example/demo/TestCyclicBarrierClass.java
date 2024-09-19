package com.example.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrierClass implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public TestCyclicBarrierClass(CyclicBarrier barrier){
        this.cyclicBarrier=barrier;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("action 1 "+Thread.currentThread().getName());
        try {
            int randomNumber = new Random().nextInt(1000);
            Thread.sleep(randomNumber);
            System.out.println("RANDOM NUMBER "+randomNumber);
            System.out.println(" get NumberWaiting: "+cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException exception){
            exception.printStackTrace();
        }
        System.out.println("completed action 1 "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            System.out.println("action 2 "+Thread.currentThread().getName());
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException exception){
            exception.printStackTrace();
        }
        System.out.println("completed action 2 "+Thread.currentThread().getName());

    }
}
