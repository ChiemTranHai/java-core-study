package com.example.demo;

public class TestClass  {
    public String var1;
    public String var2;
    public String var3;

    public TestClass(){

    }

    public TestClass(String var1) {
        this.var1 = var1;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "var1='" + var1 + '\'' +
                '}';
    }
}
