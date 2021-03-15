package com.post.db.daomp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.post.db.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
