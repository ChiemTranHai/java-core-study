package com.example.demo;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableWebMvc
public class WebConfig{
    @Bean
    void init(){
        System.out.println("configuration ne");
    }

//    @Bean
//    @ConditionalOnProperty(name = "spring.config.location")
//    PropertiesConfiguration propertiesConfiguration(@Value("${spring.config.location}") String path) throws ConfigurationException, IOException {
//        String filePath = new File(path.substring("file:".length())).getCanonicalPath();
//        PropertiesConfiguration configuration = new PropertiesConfiguration(
//                new File(filePath));
//        configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
//        return configuration;
//    }

//
//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public ScopeRequest scopeRequest() {
//        return new ScopeRequest();
//    }
//
//    @Bean
//    RequestAttributes servletRequestAttributes(HttpServletRequest request){
//        System.out.println(request.getContextPath());
//        RequestAttributes requestAttributes=new ServletRequestAttributes(request);
//        RequestContextHolder.setRequestAttributes(requestAttributes,true);
//        return requestAttributes;
//    }

    @Bean
    public Properties properties() throws IOException {
        Properties properties=new Properties();
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(inputStream);
        return properties;
    }
}