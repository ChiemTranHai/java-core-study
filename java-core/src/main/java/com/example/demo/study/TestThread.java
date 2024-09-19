package com.example.demo.study;

import com.example.demo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class TestThread {

    public static void getAllThread() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Predicate<Thread> predicate = t -> List.of("Thread-0", "Thread-1", "Thread-2", "Thread-3").contains(t.getName());
        System.out.println("------------------------------------------------------");
        threadSet.stream().filter(predicate).forEach((t) -> System.out.println(t.getName() + " : " + t.getState().name()));
    }

    public static class InnerClass {
        private int balance = 1000;
        private boolean threadFree = false;

        public synchronized void deposit(int amount) {
            while (!threadFree) {
                try {
                    System.out.println("START DEPOSIT " + Thread.currentThread().getName());
                    getAllThread();
                    if ("Thread-3".equals(Thread.currentThread().getName())) {
                        wait(10000);
                    } else {
                        wait();
                    }
                    System.out.println("END WAITING DEPOSIT " + Thread.currentThread().getName());
                    getAllThread();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            this.balance += amount;
            System.out.println("READY DEPOSIT " + Thread.currentThread().getName());
            getAllThread();
            notify();
            System.out.println("END DEPOSIT " + Thread.currentThread().getName());
            getAllThread();
            System.out.println(Thread.currentThread().getName() + ": deposit " + balance);
        }

        public synchronized void withdrawal(int amount) throws InterruptedException {
            this.balance -= amount;
            Thread.sleep(2000);
            this.threadFree = true;
            System.out.println("START WITHDRAWAL " + Thread.currentThread().getName());
            getAllThread();
            Thread.sleep(20);
            System.out.println("END SLEEP WITHDRAWAL " + Thread.currentThread().getName());
            getAllThread();
            notify();
            System.out.println("END WITHDRAWAL " + Thread.currentThread().getName());
            getAllThread();
        }

    }

    public static void threadState() throws InterruptedException {
        InnerClass innerClass = new InnerClass();
        Thread thread = new Thread(() -> {
            try {
                innerClass.withdrawal(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread1 = new Thread(() -> {
            innerClass.deposit(100);
        });
        Thread thread2 = new Thread(() -> {
            innerClass.deposit(300);
        });
        Thread thread3 = new Thread(() -> {
            innerClass.deposit(200);
        });
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Uncaught exception " + t.getName() + " : " + t.getState().name() + " is alive : " + t.isAlive());
            System.out.println(e.getMessage());
        });
        System.out.println(thread.getName() + " : " + thread.getState().name());
        System.out.println(thread1.getName() + " : " + thread1.getState().name());
        System.out.println(thread2.getName() + " : " + thread2.getState().name());
        System.out.println(thread3.getName() + " : " + thread3.getState().name());
        System.out.println("NEW");
        getAllThread();
        thread3.start();
        Thread.sleep(20);
        thread1.start();
        Thread.sleep(20);
        thread2.start();
        thread.start();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(thread3.isInterrupted());
        System.out.println(thread3.isAlive());
    }

    public static void badPracticeSynchronized() throws InterruptedException {
        final ValuePair object1 = new ValuePair();
        object1.setValue(1);

        final ValuePair object2 = new ValuePair();
        object2.setValue(5);
        final Thread thread1 = new Thread("Thread-1") {
            @Override
            public void run() {
                try {
                    object1.copy(object2);
                } catch (InterruptedException | CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        final Thread thread2 = new Thread("Thread-2") {
            @Override
            public void run() {
                object2.setValue(10);
            }
        };
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(object1);
        System.out.println(object2);

        // Based on thread 1 or thread 2 starting before we have different value
    }

    public static void testCreateAccountSameTime() {
        AccountClass accountClass = new AccountClass();
        String loginId = "loginId3";
        Runnable runnable = () -> {
            if (!accountClass.isExistsAccount(loginId)) {
                accountClass.insertAccount(loginId);
            }
        };
        List<Thread> threads = new ArrayList<>();
        IntStream.range(0, 5).forEach((index) -> {
            threads.add(new Thread(runnable));
        });
        threads.forEach(Thread::start);
        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(accountClass.getLoginIds());
    }

    public static void testSynchronized() {
        TestSynchronizedClass test = new TestSynchronizedClass();
        Thread thread = new Thread(test::action1);
//        Thread thread1= new Thread(test::action2);
        Thread thread2 = new Thread(test::action3);
//        Thread thread3= new Thread(test::action4);
        thread2.start();
        thread.start();
//        thread1.start();
//        thread3.start();
    }

    public static void testCountDownLatch() throws InterruptedException {
        TestCountDownLatch test = new TestCountDownLatch();
        Thread thread = new Thread(test::action1);
        Thread thread1 = new Thread(test::action2);
        Thread thread2 = new Thread(test::action3);
        thread1.start();
        thread.start();
        thread2.start();

    }

    public static void testPhaser() {
        Phaser phaser = new Phaser();
        new TestClassPhaser(phaser);
        new TestClassPhaser(phaser);
        new TestClassPhaser(phaser);
        new TestClassPhaser(phaser);
        new TestClassPhaser(phaser);
    }

    public static void testCyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(4);
        System.out.println(barrier.getParties());
        new TestCyclicBarrierClass(barrier);
        new TestCyclicBarrierClass(barrier);
        new TestCyclicBarrierClass(barrier);
        new TestCyclicBarrierClass(barrier);

    }
}
