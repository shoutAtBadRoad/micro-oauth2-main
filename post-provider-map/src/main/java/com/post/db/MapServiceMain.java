package com.post.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class MapServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(MapServiceMain.class,args);
    }
}
