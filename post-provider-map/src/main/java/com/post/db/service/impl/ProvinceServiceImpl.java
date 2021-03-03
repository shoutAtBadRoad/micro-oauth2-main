package com.post.db.service.impl;

import com.post.db.dao.ProvinceDao;
import com.post.db.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Resource
    private ProvinceDao provinceDao;
    @Override
    public String getProvince(String provinceId) {
        return provinceDao.getProvince(provinceId);
    }
}
