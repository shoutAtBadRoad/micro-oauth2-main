package com.jyp.bean;

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

    public <T> List<T> popItems(String listName, int num) throws NullPointerException{
//        long size = 0;
//        try{
//            size = redisTemplate.opsForList().size(listName);
//        }catch (NullPointerException n){
//            n.getCause();
//        }
        if(num > redisTemplate.opsForList().size(listName)) {
            clearList(listName);
        }
        else {
            while (num > 0) {
                if(num==1){
                    return (List<T>) redisTemplate.opsForList().leftPop(listName);
                }
                redisTemplate.opsForList().leftPop(listName);
                num--;
            }
        }
        return null;
    }
}
