package study.model;

import java.util.concurrent.*;

public class ScheduleThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
        executor.scheduleAtFixedRate(()->{
            System.out.println("print schedule ne 1"+Thread.currentThread().getName());
            System.out.println(threadPoolExecutor.getQueue().size());
            try{
                Thread.sleep(100_000);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        },1,1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(()->{
            System.out.println("print schedule ne 2"+Thread.currentThread().getName());
            System.out.println(threadPoolExecutor.getQueue().size());
        },10,1,TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(()->{
            System.out.println("print schedule ne 3"+Thread.currentThread().getName());
            System.out.println(threadPoolExecutor.getQueue().size());
        },10,1,TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(()->{
            System.out.println("print schedule ne 4"+Thread.currentThread().getName());
            System.out.println(threadPoolExecutor.getQueue().size());
            try{
                Thread.sleep(10_000);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        },2,1,TimeUnit.SECONDS);
        Thread.sleep(5_000);
        System.out.println(threadPoolExecutor.getQueue().size());
        System.out.println(threadPoolExecutor.getQueue());
        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getMaximumPoolSize());
    }
}
