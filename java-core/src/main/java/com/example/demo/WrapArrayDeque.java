package com.example.demo;

import org.springframework.lang.NonNull;

import java.util.ArrayDeque;

public class WrapArrayDeque extends ArrayDeque<String> {

    public void addFirst(String e){
        super.addFirst(e);
    }

    public void addLast(String e){
        super.addLast(e);
    }
}
