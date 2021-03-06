package com.post.db.daomp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.post.db.entityMp.StationManager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "stationManagerMapper")
public interface StationManagerMapper extends BaseMapper<StationManager> {
}
