package com.post.db.dao;

import com.post.db.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    int addOneUser(User user);

    int updateUser(User user);

    User getOneUserById(@Param("id")long id);

    User getOneUserByName(@Param("username")String username);

    User getOneUserByMobile(@Param("mobile")String mobile);

    User getOneUserByMail(@Param("mail")String mail);

    List<User> getUsers();

}
