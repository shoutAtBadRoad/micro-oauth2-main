package iyp.config;

//import com.jyp.server.MessageRepository;
//import com.jyp.server.WebSocketServer;
//import com.jyp.service.RedisService;
import iyp.server.WebSocketServer;
import iyp.service.UserMsgService;
import iyp.wsService.WsMsgDispatcher;
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
    public void setUserMsgService(UserMsgService userMsgService){
        WebSocketServer.userMsgService =  userMsgService;
    }

    @Autowired
    public void setWsDispatcher(WsMsgDispatcher wsDispatcher){
        WebSocketServer.dispatcher = wsDispatcher;
    }

//    @Autowired
//    public void setMessageRepository(MessageRepository messageRepository){
//        WebSocketServer.messageRepository = messageRepository;
//    }
//
//    @Autowired
//    public void serRedisService(RedisService redisService) {
//        WebSocketServer.redisService = redisService;
//    }

}