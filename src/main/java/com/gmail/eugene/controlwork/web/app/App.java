package com.gmail.eugene.controlwork.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = "com.gmail.eugene.controlwork",
        exclude= UserDetailsServiceAutoConfiguration.class
)
@EntityScan(basePackages = "com.gmail.eugene.controlwork.repository.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
