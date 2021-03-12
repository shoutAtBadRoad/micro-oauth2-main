package com.jyp.schedule;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.jyp.entity.UserMsg;
import com.jyp.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class MessageSchedule {

    @Scheduled(cron="0/15 * * * * *")
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
}
