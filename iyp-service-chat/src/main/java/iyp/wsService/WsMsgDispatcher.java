package iyp.wsService;

import iyp.entity.CommonWsMsg;
import iyp.entity.WsMsgType;
import iyp.wsService.impl.WsImgService;
import iyp.wsService.impl.WsMsgService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author JYP
 * @date 2021/8/5
 **/
@Component
public class WsMsgDispatcher {

//    private static WsMsgDispatcher wsMsgDispatcher = null;
//
//    private WsMsgDispatcher(){
//        //避免外部实例化
//    }
//
//    public static synchronized WsMsgDispatcher getInstance() {
//        if(wsMsgDispatcher==null){
//            wsMsgDispatcher = new WsMsgDispatcher();
//        }
//        return wsMsgDispatcher;
//    }

//    @Resource
//    private WsMsgService wsMsgService;
    @Resource
    private WsMsgService  wsMsgService;
    @Resource
    private WsImgService wsImgService;

    public void doDispatch(CommonWsMsg<?> msg ){
        String type = msg.getType();
        if(WsMsgType.MSG.getType().equals(type)){
            System.out.println("get msg");
            wsMsgService.doWork(msg);
        }else if(WsMsgType.IMAGE.getType().equals(type)){
            System.out.println("get image");
            wsImgService.doWork(msg);
        }else if(WsMsgType.GIF.getType().equals(type)){
            System.out.println("get gif");
        }else if(WsMsgType.VIDEO.getType().equals(type)){
            System.out.println("get video");
        }
    }

    public static void main(String[] args) {
//        WsMsgDispatcher instance = WsMsgDispatcher.getInstance();

//        CommonWsMsg<String> msg = new CommonWsMsg<String>(WsMsgType.MSG.getType(), "1");
//        CommonWsMsg<String> msg1 = new CommonWsMsg<>(WsMsgType.IMAGE.getType(), "2");
//        instance.doDispatch(msg);
//        instance.doDispatch(msg1);
    }

}
