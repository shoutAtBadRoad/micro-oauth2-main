package jiyip.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author JYP
 * @date 2021/3/26
 **/
@Service
public class InitApplication{

    private final User user;

    @Autowired
    public InitApplication(User user) {
        this.user = user;
    }

//    @Autowired
//    private User user;

    @PostConstruct
    public void run() {

        System.out.println(user.getName()+"的年龄是"+user.getAge());
    }
}
