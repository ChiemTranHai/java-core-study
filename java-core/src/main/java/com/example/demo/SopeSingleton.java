package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SopeSingleton {
    private int temp=23;

    @PostConstruct
    public void init(){
        temp= new Random().nextInt();
        System.out.println("tab change ne singleton "+temp);
    }

    public int getTemp(){
        int temp1=temp;
        temp=123;
        return temp1;
    }

    public String getText(){
        return "test ne "+temp;
    }
}
