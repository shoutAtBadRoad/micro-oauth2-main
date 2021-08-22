package iyp.server;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import iyp.entity.CommonWsMsg;
import iyp.entity.UserMsg;
import iyp.service.UserMsgService;
import iyp.wsService.WsMsgDispatcher;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/webSocket/{sid}")
@Component
@Data
public class WebSocketServer {

    public static UserMsgService userMsgService;

    public static WsMsgDispatcher dispatcher;

    static Log log=LogFactory.get(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    public static CopyOnWriteArraySet<WebSocketServer>  getItems() {
        return webSocketSet;
    }

    public static ConcurrentHashMap<String,Integer> roomNumber = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //消息服务列表
    private List<Integer> mid = new ArrayList<>();

    //窗口sid
    private String s_id="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) throws IOException {
        this.session = session;
        this.s_id=sid;
        webSocketSet.add(this);     //加入set中
        int n = 1;
        if (roomNumber.containsKey(this.s_id)) {
            n = roomNumber.get(sid);
            n++;
            roomNumber.put(sid,n);
        }else {
            roomNumber.put(sid,n);
        }
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + n);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        Integer n = roomNumber.get(s_id);
        roomNumber.put(s_id,n-1);
        log.info("有一连接关闭！当前在线人数为"+ (n-1));
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("收到来自窗口"+s_id+"的信息:" + session.getId() + " 说："+message);

        JSONObject jsonObject = JSON.parseObject(message);
        String clazz = (String) jsonObject.get("type");
//        WsMsgDispatcher dispatcher = WsMsgDispatcher.getInstance();
        CommonWsMsg<String> msg = new CommonWsMsg<String>();
        msg.setType(clazz);
        msg.setData(message);
        msg.setChatId(this.s_id);
        dispatcher.doDispatch(msg);
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
                }else if(item.s_id.equals(sid)){
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

