package com.example.demo;

import com.example.demo.study.TestThread;

import java.util.concurrent.CountDownLatch;

public class TestSynchronizedClass {
    private int num1;

    private final Object mutex1 = new Object();
    private final Object mutex2 = new Object();

    public synchronized void print1() {
        System.out.println("print 1 " + Thread.currentThread().getName());
        TestThread.getAllThread();
//        try {
//            Thread.sleep(5000);
//        } catch(InterruptedException exception){
//            exception.printStackTrace();
//        }
    }

    public synchronized void print2() {
        System.out.println("print 2 " + Thread.currentThread().getName());
        TestThread.getAllThread();
//        try{
//            Thread.sleep(10000);
//        } catch (InterruptedException exception){
//            exception.printStackTrace();
//        }
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void action1() {
        synchronized (mutex1) {
            this.setNum1(23);
            System.out.println("action1 " + Thread.currentThread().getName());
            TestThread.getAllThread();
            mutex1.notify();
        }
    }

    public void action2() {
        synchronized (mutex2) {
            System.out.println("action2 " + Thread.currentThread().getName());
            TestThread.getAllThread();
        }
    }

    public void action3() {
        synchronized (mutex1) {
            System.out.println("action 3 " + Thread.currentThread().getName());
            TestThread.getAllThread();
            try {
                mutex1.wait(10000000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("get num1 " + this.getNum1());
        }
    }

    public void action4() {
        synchronized (mutex1) {
            System.out.println("action 4 " + Thread.currentThread().getName());
            TestThread.getAllThread();
        }
    }
}
