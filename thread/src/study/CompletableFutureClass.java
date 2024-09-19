package study;

import java.sql.Time;
import java.util.concurrent.*;

public class CompletableFutureClass {
    public static CompletableFuture<String> generateWorld(String string){
        return CompletableFuture.completedFuture(string + " World");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // simple
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> str=CompletableFuture.supplyAsync(()->"Hello");

        System.out.println(str.get()+" World");

        CompletableFuture<String> str1=CompletableFuture.supplyAsync(()->"Hello").thenCombine(CompletableFuture.supplyAsync(()->"Word"),(string1,string2)->{
            return string1+" "+string2;
        });
        System.out.println(str1.get());
        CompletableFuture<String> str2=CompletableFuture.supplyAsync(()->"Hello").thenApply((string)->{
            return string+" World";
        });
        System.out.println(str2.get());
        CompletableFuture<Void> str3= CompletableFuture.completedFuture("Hello").thenAcceptAsync((string)->{
            System.out.println("str3 "+string + " World");
        },executor);

        CompletableFuture<String> str4=CompletableFuture.supplyAsync(()->"Hello").thenCompose(CompletableFutureClass::generateWorld);

        System.out.println("str4 "+str4.get());

        CompletableFuture<String> str5=CompletableFuture
                .supplyAsync(()->{
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Hello World";
                })
                .completeOnTimeout("Time out",1L, TimeUnit.SECONDS);
        System.out.println("str5 "+str5.get());

        CompletableFuture<String> str6=CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello World";
        }).orTimeout(1L, TimeUnit.SECONDS).exceptionally((ex)->{
            return "Time out";
        });

        System.out.println("str6 "+str6.get());

        CompletableFuture<String> compute=new CompletableFuture<>();

        compute.completeAsync(()->{
            System.out.println("test ne");
            return "test";
        });
        CompletableFuture<Void> compute1=CompletableFuture.completedFuture("message").thenAcceptAsync(s -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("tests "+Thread.currentThread().isDaemon());
        },executor);
        executor.shutdown();

        CompletableFuture<Void> compute2=CompletableFuture.completedFuture("message").thenAcceptBoth(CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "test";
        }),(s1,s2)->{
            System.out.println("s ne "+s1+" "+s2);
        });
        System.out.println("process tiep ne ");
    }
}
