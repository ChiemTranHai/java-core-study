//package com.example.demo;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import java.io.IOException;
//
//@WebFilter("/")
//public class WebDemoFilter extends HttpFilter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("vo filter ne");
//        ThreadLocalRequest.create(servletRequest,servletResponse);
//        super.doFilter(servletRequest,servletResponse,filterChain);
//    }
//}
