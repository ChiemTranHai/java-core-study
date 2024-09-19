package com.example.demo;

public class CustomUncheckedException extends RuntimeException{

    public CustomUncheckedException(String message){
        super(message);
    }

    public CustomUncheckedException(String message, Throwable cause){
        super(message, cause);
    }
}
