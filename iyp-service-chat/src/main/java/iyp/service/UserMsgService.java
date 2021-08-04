package iyp.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import iyp.entity.UserMsg;
import iyp.mapper.UserMsgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author JYP
 * @date 2021/7/28
 **/
@Service
public class UserMsgService {

    @Resource
    private UserMsgMapper userMsgMapper;

    public boolean insert(UserMsg userMsg){
        int i = userMsgMapper.insert(userMsg);
        userMsg.setItime(new DateTime().toString("YYYY-mm-DD HH:mm:SS"));
        return i==1;
    }

    public List<UserMsg> getMsg(int to){
        QueryWrapper<UserMsg> wrapper = new QueryWrapper<>();
        List<UserMsg> toId = userMsgMapper.selectList(wrapper.eq("toId", to));
        return toId;
    }

    public List<UserMsg> getMsg(int from,int to){
        QueryWrapper<UserMsg> wrapper = new QueryWrapper<>();
        List<UserMsg> toId = userMsgMapper.selectList(wrapper.eq("toId", to).eq("fromId",from)
                .or().eq("fromId", to).eq("toId", from));
//        List<UserMsg> msgs = userMsgMapper.selectList(new QueryWrapper<UserMsg>().eq("fromId", to).eq("toId", from));
//        toId.addAll(msgs);
//        toId.sort(new Comparator<UserMsg>() {
//            @Override
//            public int compare(UserMsg o1, UserMsg o2) {
//                return o1.getId() > o2.getId() ? 1 : 0;
//            }
//        });
        return toId;
    }
}
