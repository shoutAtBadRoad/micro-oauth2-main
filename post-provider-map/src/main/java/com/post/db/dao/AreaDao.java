package com.post.db.dao;

import com.post.db.entities.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AreaDao {
    public List<Area> getAreaList();
}
