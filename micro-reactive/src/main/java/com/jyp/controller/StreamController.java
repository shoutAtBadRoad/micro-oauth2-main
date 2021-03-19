package com.jyp.controller;

import com.alibaba.fastjson.JSON;
import com.jyp.cat.MessageType;
import com.jyp.entity.CommonResult;
import com.jyp.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
@Slf4j
@RequestMapping("/stream")
public class StreamController {

    @GetMapping("/pickup/{mid}")
    public CommonResult pickUpLog(@PathVariable("mid")int mid) throws IOException {

        log.info("请求推送"+mid+"类型信息服务");
        serviceProvider(mid);
        return CommonResult.success("我帮你通知了");
    }

    public void serviceProvider(int mid) throws IOException {
        CopyOnWriteArraySet<WebSocketServer> items = WebSocketServer.getItems();
        switch (mid) {
            case 1:
                for(WebSocketServer item : items) {
                    if(item.getMid().contains(1)){
                        log.info("窗口"+item.getSid()+" 有" + 1 +"类型信息服务");
                        try {
                            int[] array = new int[]{1, new Random().nextInt(1000),new Random().nextInt(1000)};
                            item.sendMessage(JSON.toJSONString(array));
//                            item.sendMessage(JSON.toJSONString("有人取件了，所以向你发送一条记录通知一下"));
                        }catch (IOException e){
                            log.error("推送失败");
                        }
                    }
                }
                break;
            case 2:
                for(WebSocketServer item : items) {
                    if(item.getMid().contains(2)){
                        log.info("窗口"+item.getSid()+" 有" + 2 +"类型信息服务");
                        try {
                            item.sendMessage(JSON.toJSONString("最新出库量趋势已统计好"));
                        }catch (IOException e){
                            log.error("推送失败");
                        }
                    }
                }
                break;
            case 3: break;
            case 4: break;
        }
    }

}
