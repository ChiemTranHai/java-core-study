package com.example.demo;

import java.util.concurrent.Phaser;

public class TestClassPhaser implements Runnable {
    private Phaser phaser;

    public TestClassPhaser(Phaser phaser) {
        this.phaser = phaser;
        this.phaser.register();
        new Thread(this).start();
        System.out.println("getRegisteredParties "+this.phaser.getRegisteredParties()+ Thread.currentThread().getName());

    }

    @Override
    public void run() {
        System.out.println("Action 1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("getArrivedParties "+phaser.getArrivedParties()+ Thread.currentThread().getName());
        System.out.println("getUnarrivedParties "+phaser.getUnarrivedParties()+ Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();

        System.out.println("Action 2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("getArrivedParties "+phaser.getArrivedParties()+ Thread.currentThread().getName());
        System.out.println("getUnarrivedParties "+phaser.getUnarrivedParties()+ Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
        System.out.println("Action 3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("getArrivedParties "+phaser.getArrivedParties()+ Thread.currentThread().getName());
        System.out.println("getUnarrivedParties "+phaser.getUnarrivedParties()+ Thread.currentThread().getName());
        phaser.arriveAndDeregister();
        System.out.println("Deregister getRegisteredParties "+this.phaser.getRegisteredParties()+ Thread.currentThread().getName());

        if (phaser.isTerminated()){
            System.out.println("Completed "+ Thread.currentThread().getName());
        }

    }
}
