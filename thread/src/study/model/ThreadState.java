package study.model;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class ThreadState {

    static void getAllThread() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Predicate<Thread> predicate = t -> List.of("Thread-0", "Thread-1", "Thread-2", "Thread-3", "Thread-4", "Thread-5").contains(t.getName());
        System.out.println("------------------------------------------------------");
        threadSet.stream().filter(predicate).forEach((t) -> System.out.println(t.getName() + " : " + t.getState().name()));
    }

    private static class InnerClass {
        private final Lock lock = new ReentrantLock();
        private final Condition notWithdraw = lock.newCondition();
        private int amount = 0;
        private AtomicInteger amountV2= new AtomicInteger();
        private int amountNeedToWithdraw = 0;

        public int getAmount() {
            return this.amount;
        }

        public void withdraw(int amount) {
            this.amountNeedToWithdraw = amount;
            System.out.println("withdraw " + Thread.currentThread().getName() + " :" + Thread.currentThread().getState());
            while (this.amount < amount) {
                try {
                    lock.lock();
                    getAllThread();
                    try {
                        notWithdraw.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("withdraw success");
            getAllThread();
            try {
                lock.lock();
                this.amount -= amount;
                notWithdraw.signal();
            } finally {
                lock.unlock();
            }
            getAllThread();
        }

        private void deposit(int amount) {
            System.out.println("deposit " + Thread.currentThread().getName() + " :" + Thread.currentThread().getState());
            try {
                lock.lock();
                getAllThread();
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.amount += amount;
                if (this.amount >= this.amountNeedToWithdraw) {
                    notWithdraw.signal();
                }
            } finally {
                lock.unlock();
            }
            getAllThread();
        }

        public int getAmountV2() {
            return this.amountV2.get();
        }

        public void withdrawV2(int amount) {
            while (this.amountV2.get() < amount) {
                Thread.onSpinWait();
            }
            System.out.println("withdraw success");
            this.amountV2.addAndGet(-amount);
        }

        public void depositV2(int amount) {
            this.amountV2.addAndGet(amount);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InnerClass innerClass = new InnerClass();
        Thread t1 = new Thread(() -> {
            innerClass.withdrawV2(10_000);
        });
        Thread t2 = new Thread(() -> {
            innerClass.depositV2(500);
        });
        Thread t3 = new Thread(() -> {
            innerClass.depositV2(2_000);
        });
        Thread t4 = new Thread(() -> {
            innerClass.depositV2(15_000);
        });
        Thread t5 = new Thread(() -> {
            innerClass.depositV2(25_000);
        });
        Thread t6 = new Thread(() -> {
            innerClass.withdrawV2(13_000);
        });
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(10_000);
        t4.start();
        t5.start();
        t6.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        System.out.println("Amount remaining " + innerClass.getAmountV2());
    }
}
