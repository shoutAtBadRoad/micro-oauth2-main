package iyp.wsService.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import iyp.entity.CommonWsMsg;
import iyp.entity.UserMsg;
import iyp.entity.WsMsgType;
import iyp.mapper.UserMsgMapper;
import iyp.server.WebSocketServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JYP
 * @date 2021/8/5
 **/
@Service
public class WsMsgService {

    @Resource
    private UserMsgMapper msgMapper;

//    private static WsMsgService instance = null;
//
//    private WsMsgService(){
//    }
//
//    public static synchronized WsMsgService getInstance(){
//        if(instance==null){
//            instance = new WsMsgService();
//        }
//        return instance;
//    }

    public void doWork(CommonWsMsg comMsg){
        UserMsg msg = JSON.parseObject((String) comMsg.getData(), UserMsg.class);

        msg.setItime(new DateTime().toString());
        msg.setType(WsMsgType.MSG.getType());

        comMsg.setData(msg);

        msgMapper.insert(msg);
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(comMsg), comMsg.getChatId());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println();
    }

}
