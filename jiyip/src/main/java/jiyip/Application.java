package jiyip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.swing.*;

/**
 * @author JYP
 * @date 2021/3/26
 **/
@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }

}
