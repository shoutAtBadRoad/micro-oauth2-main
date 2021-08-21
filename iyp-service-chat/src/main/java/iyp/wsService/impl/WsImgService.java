package iyp.wsService.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import iyp.entity.CommonWsMsg;
import iyp.entity.ImageMsg;
import iyp.entity.Photo;
import iyp.entity.UserMsg;
import iyp.mapper.PhotoMapper;
import iyp.mapper.UserMsgMapper;
import iyp.server.WebSocketServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JYP
 * @date 2021/8/6
 **/
@Service
public class WsImgService {

//    private static WsImgService instance = null;

    private final ConcurrentHashMap<String,String> imgs = new ConcurrentHashMap<>();

    private final Map<String,String> images = new HashMap<>();

    private final Map<String,Integer> finish = new HashMap<>();

    public ConcurrentHashMap<String, String> getImgs() {
        return imgs;
    }

//    private WsImgService(){
//
//    }
//
//    public static synchronized WsImgService getInstance(){
//        if(instance==null){
//            instance = new WsImgService();
//        }
//        return instance;
//    }

    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private UserMsgMapper userMsgMapper;

    public void doWork(CommonWsMsg msg){

        String s = msg.getData().toString();
        ImageMsg imageMsg = JSON.parseObject(s, ImageMsg.class);
        if(imageMsg.getNum()==0){
            images.put(String.valueOf(imageMsg.getId()),"");
            finish.put(String.valueOf(imageMsg.getId()),Integer.valueOf(imageMsg.getContent()));
        }else{
            images.put(String.valueOf(imageMsg.getId()),
                    images.get(String.valueOf(imageMsg.getId()))+imageMsg.getContent());
            finish.put(String.valueOf(imageMsg.getId()),
                    finish.get(String.valueOf(imageMsg.getId()))-1);
        }

        try {
            if(finish.get(String.valueOf(imageMsg.getId()))==0) {
                imageMsg.setContent(images.get(String.valueOf(imageMsg.getId())));
                WebSocketServer.sendInfo(JSON.toJSONString(new CommonWsMsg<ImageMsg>("img",imageMsg,msg.getChatId())),
                        msg.getChatId());
                UserMsg msg1 = new UserMsg(imageMsg);
                msg1.setItime(DateTime.now().toString("yyyy-MM-dd hh:MM:ss"));
                msg1.setContent(imageMsg.getId());
                writeLogs(msg1,images.get(String.valueOf(imageMsg.getId())));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void writeLogs(UserMsg msg,String img){
        photoMapper.insert(new Photo(msg.getContent(),img));
        userMsgMapper.insert(msg);
    }

}
