package com.post.db.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:config/user.properties")
public class PropertiesConfig {
    @Value("${selfUrl}")
    private String selfUrl;
}
