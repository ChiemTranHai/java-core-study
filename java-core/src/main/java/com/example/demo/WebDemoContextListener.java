package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class WebDemoContextListener extends RequestContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("context init ne ");
//    }

}
