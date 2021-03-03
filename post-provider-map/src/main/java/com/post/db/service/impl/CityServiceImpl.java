package com.post.db.service.impl;

import com.post.db.dao.CityDao;
import com.post.db.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityDao cityDao;
    @Override
    public String getCityName(String CityId) {
        return cityDao.getCityName(CityId);
    }
}
