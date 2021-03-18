package com.jyp.schedule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.jyp.entity.UserMsg;
import com.jyp.server.WebSocketServer;
import com.jyp.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class MessageSchedule {

    @Resource
    private RedisService redisService;

//    @Scheduled(cron="0/15 * * * * *")
    public void runTask() throws IOException {
        log.info(Thread.currentThread().getId()+":开始工作");
        UserMsg userMsg = UserMsg.builder().id(new Random().nextInt(9999))
                .fromId(-99).toId(-1).timestamp(DateUtil.now())
                .text(new Random().nextInt(9999) + "").build();
        String jsonStr = JSON.toJSONString(userMsg);
        CopyOnWriteArraySet<WebSocketServer> items = WebSocketServer.getItems();
        if(items.size()!=0){
            for(WebSocketServer item : items){
                item.sendMessage(jsonStr);
            }
        }
    }

    @Scheduled(cron="0/2 * * * * *")
    public void runTask1() throws IOException {
        log.info(Thread.currentThread().getId()+":开始工作");
        if(redisService.exist()) {
            List<String> list = redisService.getList();
            String[] array = list.toArray(new String[list.size()]);
//        String[] array = new String[]{"123","123"};
            String jsonStr = JSON.toJSONString(array);
            CopyOnWriteArraySet<WebSocketServer> items = WebSocketServer.getItems();
            if (items.size() != 0) {
                for (WebSocketServer item : items) {
                    item.sendMessage(jsonStr);
                }
            }
        }else {
            log.info("暂时没有缓存记录");
        }
    }


}
