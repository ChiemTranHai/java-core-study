//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.RequestContextListener;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//import java.util.Random;
//
////
//@Component
////@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class ScopeRequest {
//
//
//    @Autowired
//    private HttpServletRequest servletRequest;
//
//    @Autowired
//    private TestService testService;
//
//    private int temp =24;
////    @PostConstruct
////    public void init(){
////        temp= new Random().nextInt();
////        System.out.println("tab change ne request "+temp);
////    }
//
//
//    public int getTemp(){
//        return temp;
//    }
//
//    public String getText(){
//        return testService.getText();
//    }
//
//    public String getHeader(){
//        return servletRequest.getHeader("Host");
//    }
//}
