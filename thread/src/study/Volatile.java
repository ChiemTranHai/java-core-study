package study;

import study.model.TestVolatileClass;

public class Volatile {

    public static void testVolatile(){
        TestVolatileClass testVolatileClass=new TestVolatileClass();
        new Thread(()->{
            while (testVolatileClass.getNum2()!=5){}
            System.out.println(testVolatileClass.total());

        }).start();
        new Thread(()->{
            for (int i = 0; i < 1_000_000; i++) {
                testVolatileClass.increment();
            }
            testVolatileClass.update(4,5,6);
        }).start();
    }
}
