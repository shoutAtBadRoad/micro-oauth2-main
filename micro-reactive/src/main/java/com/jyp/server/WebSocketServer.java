package com.jyp.server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.jyp.cat.MessageType;
import com.jyp.entity.UserMsg;
import com.jyp.service.RedisService;
import lombok.Data;
import org.springframework.stereotype.Component;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;



@ServerEndpoint(value = "/webSocket/{sid}")
@Component
@Data
public class WebSocketServer {

    //消息mongodb服务
    public static MessageRepository messageRepository;

    //redis服务
    public static RedisService redisService;

    static Log log=LogFactory.get(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    public static CopyOnWriteArraySet<WebSocketServer>  getItems() {
        return webSocketSet;
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //消息服务列表
    private List<Integer> mid = new ArrayList<>();

    //窗口sid
    private String sid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) throws IOException {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
        this.mid.add(MessageType.LASTESTOUTLOG.getMid());
        this.mid.add(MessageType.OUTTREND.getMid());
        this.mid.add(MessageType.INTREND.getMid());
        this.mid.add(MessageType.PEOPLENUM.getMid());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("收到来自窗口"+sid+"的信息:"+message);
        Object parse1 = JSON.parse(message);
        String s = parse1.toString();
        UserMsg userMsg = JSON.parseObject(s,UserMsg.class);
        userMsg.setId(mid.get(0));
        //历史消息加入队列
//        Mono<UserMsg> msgMono = messageRepository.save(userMsg);
//        msgMono.subscribe(System.out::println);
        message = JSON.toJSONString(userMsg);

        //群发消息
        if(userMsg.getToId()==-1) {
            for (WebSocketServer item : webSocketSet) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            sendInfo(message,userMsg.getToId()+"");
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}


