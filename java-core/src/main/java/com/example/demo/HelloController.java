package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
public class HelloController {

    @Autowired
    private Properties properties;



//    @Autowired
//    private ScopeRequest scopeRequest;
//
//    @Autowired
//    private SopeSingleton sopeSingleton;
//
//    @GetMapping("")
//    public String helloWorld(){
////        System.out.println("singleton "+sopeSingleton.getTemp());
////        System.out.println("request "+scopeRequest.getTemp());
//        return sopeSingleton.getText();
//    }
//
@Autowired
private TestService testService;

@Autowired
    private TestAsync testAsync;

    @GetMapping()
    public Properties getTest() {
        return properties;
    }

    @GetMapping("/refresh")
    public void refresh() throws IOException {
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(inputStream);
    }

    @GetMapping("hello")
    public String getResponse(){
        return "message ne ban!";
    }

    @GetMapping("async")
    public String testAsync() throws InterruptedException {
        for (int i=0;i<14;i++){
//            testService.getText();
            testAsync.testAsync();
            System.out.println("i ne "+i);
        }
        return "test async ne";
    }
}
