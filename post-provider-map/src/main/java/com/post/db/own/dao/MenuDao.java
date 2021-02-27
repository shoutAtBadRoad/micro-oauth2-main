package com.post.db.own.dao;

import com.post.db.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenuDao {
    public List<Menu> getMenuList();
}
