package com.example.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
public class TestService {

    private int temp;

    @PostConstruct
    public void init(){
        System.out.println("test service init");
        temp=new Random().nextInt();
    }

    @Async
    public String getText(){
        System.out.println("test-"+Thread.currentThread().getId()+Thread.currentThread().getName());
        return "test ne "+temp;
    }
}
