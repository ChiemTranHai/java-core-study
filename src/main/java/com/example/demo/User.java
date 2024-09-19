//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.util.Random;
//
//@RestController
//@RequestMapping("/user")
//public class User {
//
//    @Autowired
//    private SopeSingleton sopeSingleton;
//
//    @Autowired
//    private ScopeRequest scopeRequest;
//
//    @GetMapping()
//    public Object getUserId() {
////        System.out.println("user singleton "+sopeSingleton.getTemp());
////        System.out.println("user request "+scopeRequest.getTemp());
//        return sopeSingleton.getTemp();
//    }
//}
