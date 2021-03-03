package com.post.db.daomp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.post.db.entities.PackStored;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PackLogMapper extends BaseMapper<PackStored> {
}
