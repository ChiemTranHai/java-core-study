//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.SpringApplicationRunListener;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.event.SpringApplicationEvent;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.RequestContextListener;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRequestEvent;
//import javax.servlet.ServletRequestListener;
//import javax.servlet.annotation.WebListener;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Objects;
//
////@WebListener
//public class WebDemoListener implements ServletRequestListener {
//
//    private ServletContext servletContext;
//    @Override
//    public void requestInitialized(ServletRequestEvent sre) {
//        servletContext= sre.getServletContext();
//        System.out.println("vo day requestInitialized ");
////        requestAttributes.setAttribute("test",ScopeRequest.class,0);
////        Objects.requireNonNull(RequestContextHolder.currentRequestAttributes()).setAttribute("test",ScopeRequest.class,0);
//        try {
//            getLanguage();
//        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
//                 InstantiationException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getLanguage() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
////        ScopeRequest scopeRequest= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getBean(ScopeRequest.class);
////        System.out.println(scopeRequest.getHeader());
//    }
//}
