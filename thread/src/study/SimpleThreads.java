package study;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class SimpleThreads {

    static void getAllThread() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Predicate<Thread> predicate = t -> List.of("Thread-0", "Thread-1", "Thread-2", "Thread-3").contains(t.getName());
        System.out.println("------------------------------------------------------");
        threadSet.stream().filter(predicate).forEach((t) -> System.out.println(t.getName() + " : " + t.getState().name()));
    }

    // Display a message, preceded by
    // the name of the current thread
    static void threadMessage(String message) {
        String threadName =
                Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    private static class MessageLoop
            implements Runnable {
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };
            try {
                for (int i = 0;
                     i < importantInfo.length;
                     i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                while (!Thread.currentThread().isInterrupted()){}
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().isInterrupted());
                threadMessage("I wasn't done!");
//                System.out.println(Thread.currentThread().isInterrupted());
            }
        }
    }

    public static void main(String args[])
            throws InterruptedException {

        // Delay, in milliseconds before
        // we interrupt MessageLoop
        // thread (default one hour).
        long patience = 1000 * 6;

        // If command line argument
        // present, gives patience
        // in seconds.
        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                System.out.println("interrupt");
                getAllThread();
//                System.out.println("isInterrupted "+Thread.currentThread().isInterrupted());
//                while (!t.isInterrupted()){
//                    System.out.println("vo day ne");
//                }
                System.out.println("finish ");
                getAllThread();
//                t.join();
//                System.out.println("join");
//                getAllThread();
            }
            System.out.println("isAlive isInterrupted "+t.isInterrupted());
            getAllThread();
        }
        getAllThread();

        System.out.println("is not Alive isInterrupted "+t.isInterrupted());

        threadMessage("Finally!");
    }
}
