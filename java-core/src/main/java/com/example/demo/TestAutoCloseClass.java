package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class TestAutoCloseClass implements AutoCloseable {
    private Map<String, String> cached;

    public TestAutoCloseClass() {
        this.cached = new HashMap<>();
    }

    public void add(String key, String value){
        this.cached.put(key, value);
    }

    public String get(String key){
        return this.cached.get(key);
    }

    @Override
    public void close() {
        System.out.println("auto clear cached");
        this.cached.clear();
        this.cached = null;
        this.cached.get("test");
    }
}
