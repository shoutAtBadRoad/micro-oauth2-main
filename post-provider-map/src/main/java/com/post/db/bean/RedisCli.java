package com.post.db.bean;

import com.post.db.entity.RedisCat;
import com.post.db.utils.YSTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCli {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String s, Object obj, long time, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(s,obj,time,timeUnit);
    }

    public void set(String s, Object obj){
        redisTemplate.opsForValue().set(s,obj);
    }

    public <T> T get(String key){
       return (T) redisTemplate.opsForValue().get(key);
    }

    public void expired(String key){
        redisTemplate.delete(key);
    }

    public <T> void putList(String listName, List<T> list){
        redisTemplate.opsForList().rightPush(listName, list);
    }

    public <T> List<T> getList(String listName){
        return (List<T>) redisTemplate.opsForList().range(listName,0,-1);
    }

    public void clearList(String listName){
        while (redisTemplate.opsForList().size(listName)>0){
            redisTemplate.opsForList().leftPop(listName);
        }
    }
}
