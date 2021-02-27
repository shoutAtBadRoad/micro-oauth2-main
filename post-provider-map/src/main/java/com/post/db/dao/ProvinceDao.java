package com.post.db.dao;

import com.post.db.entities.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProvinceDao {
    public String getProvince(@Param("provinceId")String provinceId);

    public List<Province> getProvinceList();
}
