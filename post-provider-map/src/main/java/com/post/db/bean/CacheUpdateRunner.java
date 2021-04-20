package com.post.db.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
public class CacheUpdateRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Set<String> keys = redisTemplate.keys("*");//清空redis数据库中所有的键值对
        redisTemplate.delete(keys);
        System.out.println("全部删除了");
    }
}
