package com.jyp.service;

import com.jyp.bean.RedisCli;
import com.jyp.cat.RedisCat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RedisService {

    private final String listName = RedisCat.LatestOutList+1;

    @Resource
    private RedisCli redisCli;

    public List<String> getList() {

        List<String> list = redisCli.popItems(listName, 1);
        if(list==null){
            redisCli.expired(RedisCat.LatestOutTime+1);
        }
        return list;
    }

    public boolean exist() {

        Object o = redisCli.get(RedisCat.LatestOutTime + 1);
        return o != null;
    }

}
