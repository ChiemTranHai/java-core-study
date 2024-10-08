package com.example.demo;

import java.io.Serial;
import java.io.Serializable;

public class SimpleClass implements Serializable, Comparable<SimpleClass> {

    @Serial
    private static final long serialVersionUID = -266706354210367639L;

    private String str1;
    private String str2;

    private String str3;

    private Integer number;

    public SimpleClass(){

    }

    public SimpleClass(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public SimpleClass(String str1, String str2, String str3) {
        this(str1, str2);
        this.str3 = str3;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SimpleClass{" +
                "str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                ", str3='" + str3 + '\'' +
                '}';
    }

    @Override
    public int compareTo(SimpleClass o) {
        return this.number.compareTo(o.getNumber());
    }
}
