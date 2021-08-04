package iyp.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import iyp.entity.iFriend;
import iyp.mapper.FrdMapper;
import iyp.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JYP
 * @date 2021/7/30
 **/
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FrdMapper frdMapper;

    @Override
    public List<iFriend> getFrdList(int userId) {
//        return frdMapper.selectList(new QueryWrapper<iFriend>().eq("host",userId));
        return frdMapper.getFrdList(userId);
    }

    @Override
    public boolean addFrd(int host, int frd) {
        iFriend iFriend = new iFriend();
        iFriend.setHost(host);
        iFriend.setFriend(frd);
        iFriend.setChatId(UUID.randomUUID().toString());
        return 1==frdMapper.insert(iFriend);
    }
}
