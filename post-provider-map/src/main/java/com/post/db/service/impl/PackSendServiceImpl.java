package com.post.db.service.impl;

import com.post.db.dao.PackSendLogDao;
import com.post.db.dao.StationDao;
import com.post.db.entities.Smap;
import com.post.db.entities.Station;
import com.post.db.service.PackSendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackSendServiceImpl implements PackSendService {

    @Resource
    private StationDao stationDao;
    @Resource
    private PackSendLogDao packSendLogDao;

    @Override
    public Map<String, Object> getPackWillArrive(int stationId) {

        Map<String,Object> map = new HashMap<>();

        // 得到驿站信息
        Station station = stationDao.getStationById(stationId);
        // 根据驿站信息查询未来将到达的快件数
        List<Smap> list = packSendLogDao.getPackNotArrived(stationId,station.getAreaId());
        //将信息装入返回集和
        map.put("PackWillArrive",list);

        return map;
    }
}
