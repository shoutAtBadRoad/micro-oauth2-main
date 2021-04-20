package jiyip.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author JYP
 * @date 2021/3/26
 **/
//@ConstructorBinding
//@ConfigurationProperties(value = "user", prefix = "user")
@Component
@PropertySource(value = "user.yml")
public class User {

    @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
