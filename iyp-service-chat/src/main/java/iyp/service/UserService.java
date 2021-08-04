package iyp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import iyp.entity.User;
import iyp.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JYP
 * @date 2021/7/29
 **/
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User login(User u){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", u.getUserName()).eq("password", u.getPassWord()));
        return user;
    }
}
