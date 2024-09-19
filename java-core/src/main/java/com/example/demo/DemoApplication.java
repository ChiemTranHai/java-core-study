package com.example.demo;

import com.example.demo.study.TestThread;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.SQLOutput;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Logger;
import java.util.stream.IntStream;


@SpringBootApplication(scanBasePackages = {"com.example"})
@ServletComponentScan(basePackages = {"com.example"})
@EnableAsync
public class DemoApplication {
    private final static Logger LOGGER = Logger.getLogger(DemoApplication.class.getName());


    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        TestThread.threadState();

//        SpringApplication.run(DemoApplication.class, args);
    }
}