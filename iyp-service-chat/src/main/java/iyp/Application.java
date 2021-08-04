package iyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JYP
 * @date 2021/7/28
 **/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");
        SpringApplication.run(Application.class,args);
    }
}
