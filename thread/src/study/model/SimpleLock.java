package study.model;

public class SimpleLock {

    private boolean isLocked = true;

    public void lock() {
        synchronized (this) {
            while (isLocked) {
                try {
                    System.out.println("vo day khum "+Thread.currentThread().getName());
                    this.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            this.isLocked = true;
        }

//        synchronized (this) {
//            System.out.println(Thread.currentThread().getName());
//
//        }
    }

    public void unLock() {
        System.out.println("ünCLock "+Thread.currentThread().getName());
        synchronized (this) {
            this.isLocked = false;
            this.notifyAll();
        }
    }
}
