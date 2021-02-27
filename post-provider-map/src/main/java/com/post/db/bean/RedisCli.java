package com.post.db.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCli {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String s, Object obj, long time, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(s,obj);
    }

    public Object get(String key){
       return redisTemplate.opsForValue().get(key);
    }

    public void expired(String key){
        redisTemplate.delete(key);
    }

}
