package study.model;

public class TestVolatileRunner implements Runnable{

    private TestVolatileClass testVolatileClass;

    public TestVolatileRunner(TestVolatileClass testVolatileClass) {
        this.testVolatileClass = testVolatileClass;
    }

    public void run() {
        System.out.println("start Thread");
        for (long i=0;i<1_000_000;i++){
            testVolatileClass.increment();
        }
        testVolatileClass.stop();
        System.out.println("end thread");
    }
}
