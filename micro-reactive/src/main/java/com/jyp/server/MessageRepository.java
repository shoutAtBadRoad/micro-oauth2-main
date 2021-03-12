package com.jyp.server;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.jyp.entity.UserMsg;
import com.jyp.repository.MsgMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "messageRepository")
public class MessageRepository {

    @Resource
    private MsgMongoRepository msgMongoRepository;

    public Mono<UserMsg> save(UserMsg userMsg){
        userMsg.setTimestamp(DateUtil.now());
        return msgMongoRepository.save(userMsg);
    }

    public Mono<UserMsg> get(long id){
        return msgMongoRepository.findById(String.valueOf(id));
    }

}
