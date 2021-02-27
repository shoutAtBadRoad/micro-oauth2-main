package com.post.db.service;

import com.github.pagehelper.PageInfo;
import com.post.db.entity.CommonResult;
import com.post.db.entity.QueryInfo;
import com.post.db.entity.User;

import java.util.List;

public interface UserService {

    int addOneUser(User user);

    CommonResult registerUser(User user);

    PageInfo<User> getUsers(QueryInfo queryInfo);

    int reviseMobile(String mobile,int id);

    int revisePassword(String password,int id);

    int reviseRole(String role,int id);

    int reviseUsername(String username,int id);

}
