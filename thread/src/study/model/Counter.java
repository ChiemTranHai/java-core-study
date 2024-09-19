package study.model;

public class Counter {
    // default annotation not work need to add VM option -XX:-RestrictContended to enable it.
    @jdk.internal.vm.annotation.Contended
    public volatile long count1 = 0;
//    @jdk.internal.vm.annotation.Contended
    public volatile long count2 = 0;

}