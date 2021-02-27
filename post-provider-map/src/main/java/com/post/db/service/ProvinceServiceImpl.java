package com.post.db.service;

import com.post.db.dao.ProvinceDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProvinceServiceImpl implements ProvinceService{
    @Resource
    private ProvinceDao provinceDao;
    @Override
    public String getProvince(String provinceId) {
        return provinceDao.getProvince(provinceId);
    }
}
