package post.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Oauth2ApiMain {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ApiMain.class,args);
    }
}
