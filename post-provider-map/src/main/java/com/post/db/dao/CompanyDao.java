package com.post.db.dao;

import com.post.db.entities.Smap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CompanyDao {
    public List<Smap> getCompanyList();
}
