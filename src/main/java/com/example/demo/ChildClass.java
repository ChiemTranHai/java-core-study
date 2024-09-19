package com.example.demo;

public class ChildClass extends ParentClass{
    @Override
    public void print() {
        System.out.println("ChildClass");
        super.print();
    }
}
