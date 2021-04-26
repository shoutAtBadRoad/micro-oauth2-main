package com.post.db.daomp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.post.db.daomp.entity.packStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface packStatusMapper extends BaseMapper<packStatus> {
}
