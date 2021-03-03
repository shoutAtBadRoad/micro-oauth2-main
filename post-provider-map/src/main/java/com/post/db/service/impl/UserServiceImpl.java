package com.post.db.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.post.db.dao.UserDao;
import com.post.db.entities.Shelf;
import com.post.db.entity.CommonResult;
import com.post.db.entity.QueryInfo;
import com.post.db.entity.User;
import com.post.db.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public int addOneUser(User user) {
        return userDao.addOneUser(user);
    }

    @Override
    public CommonResult registerUser(User user) {
        User user1 = userDao.getOneUserByName(user.getUsername());
        if(user1!=null){
            return CommonResult.failed("此用户名已存在");
        }
        User user2 = userDao.getOneUserByMobile(user.getMobile());
        if(user2!=null){
            return CommonResult.failed("此手机号已被使用");
        }
        User user3 = userDao.getOneUserByMail(user.getMail());
        if(user3!=null){
            return CommonResult.failed("该邮箱已被使用");
        }
        int i = userDao.addOneUser(user);
        if(i==1){
            return CommonResult.success("注册成功");
        }
        return CommonResult.failed("网络异常");
    }

    @Override
    public PageInfo<User> getUsers(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPagenum(), queryInfo.getPagesize());
        //查询一定要放在startPage之后
        List<User> users = userDao.getUsers();
        users.forEach(user -> {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        });
        return new PageInfo<>(users);
    }

    @Override
    public int reviseMobile(String mobile, int id) {
        User user = userDao.getOneUserById(id);
        User user1 = userDao.getOneUserByMobile(mobile);
        if(user1!=null){
            return 404;
        }else {
            user.setMobile(mobile);
            return userDao.updateUser(user);
        }
    }

    @Override
    public int revisePassword(String password, int id) {
        User user = userDao.getOneUserById(id);
        user.setPassword(password);
        return userDao.updateUser(user);
    }

    @Override
    public int reviseRole(String role, int id) {
        User user = userDao.getOneUserById(id);
        user.setRole(role);
        return userDao.updateUser(user);
    }

    @Override
    public int reviseUsername(String username, int id) {
        User user = userDao.getOneUserById(id);
        User user1 = userDao.getOneUserByName(username);
        if(user1!=null){
            return 404;
        }else {
            user.setUsername(username);
            return userDao.updateUser(user);
        }
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
