package study.model;

public class TestVolatileClass {
    private  int num1 = 1;
    private volatile int num2 = 1;
    private  long num3 = 1;
    private boolean isRun;

    public int total() {
        System.out.println(num1+" "+num2+" "+num3);
        return num1 + num2 + (int) num3;
    }

    public void print() {
        System.out.println("print " + this.num3);
    }

    public void update(int num) {
        this.num3 = num;
    }

    public void update(int num1, int num2, long num3) {
        this.num2 = num2;
        this.num1 = num1;
        this.num3 = num3;

    }

    public void stop() {
        this.isRun = true;
    }

    public boolean isRun() {
        return isRun;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public long getNum3() {
        return num3;
    }

    public void increment(){
        this.num1++;
    }

    public void setNum2(){
        this.num2=40;
    }
}
