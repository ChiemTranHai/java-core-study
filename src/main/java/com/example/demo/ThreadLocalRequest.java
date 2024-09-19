package com.example.demo;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ThreadLocalRequest implements AutoCloseable {

    private ServletRequest request;
    private ServletResponse response;

    private static final ThreadLocal<ThreadLocalRequest> store = new ThreadLocal<>();

    public ThreadLocalRequest(ServletRequest request, ServletResponse response) {

    }

    public static void create(ServletRequest request, ServletResponse response) {
        store.set(new ThreadLocalRequest(request, response));
    }

    public static ThreadLocalRequest getCurrentValueThread() {
        return store.get();
    }

    @Override
    public void close() throws Exception {
        store.remove();
    }

    public ServletRequest getRequest() {
        return request;
    }

    public ServletResponse getResponse() {
        return response;
    }
}
