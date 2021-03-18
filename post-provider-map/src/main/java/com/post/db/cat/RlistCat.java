package com.post.db.cat;

import cn.hutool.core.collection.CollUtil;
import com.post.db.bean.RedisCli;
import com.post.db.entities.PackLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class RlistCat {

    @Resource
    private RedisCli redisCli;

    // 存放一条记录,最新取件记录
    public void pushItem(int stationId, PackLog packLog) {
        Object o = redisCli.get(RedisCat.LatestOutTime + stationId);
        if(o!=null) {
            log.info("已经有访问后的缓存，记录也向缓存中添加");
            String ListName = RedisCat.LatestOutList + stationId;
            // 去除最旧的一条记录
            redisCli.popItems(ListName, 1);
            // 添加最新的一条记录
            redisCli.putList(ListName,
                    CollUtil.toList(packLog.getPackId(), packLog.getCurDate()));
        }else {
            log.info("并没有人访问此类记录，因此不向缓存中添加记录");
        }
    }

}
