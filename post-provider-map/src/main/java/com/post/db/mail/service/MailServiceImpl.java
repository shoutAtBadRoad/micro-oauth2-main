package com.post.db.mail.service;

import cn.hutool.core.lang.Assert;
import com.post.db.bean.RedisCli;
import com.post.db.dao.UserDao;
import com.post.db.entity.User;
import com.post.db.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService{

    @Resource
    private UserDao userDao;
    @Autowired
    private RedisCli redisCli;

    @Override
    public String getEmailCode(String mail) {
//        User user = userDao.getOneUserByMail(mail);
//        if(user!=null){
//            return null;
//        }else{
            String code = RandomUtil.getrandom();
            redisCli.set(mail,code,180, TimeUnit.SECONDS);
//            user.setCode(code);
//            int i = userDao.updateUser(user);
//            System.out.println(i);
            return code;
//        }
    }

    @Override
    public boolean testCode(String mail, String code) {
        String originCode = redisCli.get(mail);
//        String s = Assert.notNull(originCode, "验证码不存在");
        if(originCode.equals(code)){
            redisCli.expired(mail);
            System.out.println(originCode);
            return true;
        }
        return false;
    }
}
