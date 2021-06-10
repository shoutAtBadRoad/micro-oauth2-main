package com.yt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PreApplication {
    public static void main(String[] args) {
        SpringApplication.run(PreApplication.class,args);
    }
}
