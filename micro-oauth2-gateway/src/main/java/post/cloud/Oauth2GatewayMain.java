package post.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import post.cloud.filter.MyCorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class Oauth2GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2GatewayMain.class,args);
    }

//    @Bean
//    public MyCorsFilter myCorsFilter(){
//        return new MyCorsFilter();
//    }
}
