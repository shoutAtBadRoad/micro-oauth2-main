package com.jyp.config;

import com.jyp.server.MessageRepository;
import com.jyp.server.WebSocketServer;
import com.jyp.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebsocketConfiguratioin {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository){
        WebSocketServer.messageRepository = messageRepository;
    }

    @Autowired
    public void serRedisService(RedisService redisService) {
        WebSocketServer.redisService = redisService;
    }

}