package com.post.db.service;

import com.post.db.dao.PackStatisticDao;
import com.post.db.dao.PackageDao;
import com.post.db.entities.PackSt;
import com.post.db.entities.TimeMap;
import com.post.db.utils.SmoothUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackStatisticServiceImpl implements PackStatisticService{
    @Resource
    private PackStatisticDao packStatisticDao;
    @Resource
    private PackageDao packageDao;

    @Override
    public int addOneInPackStatistic(PackSt packSt) {
        return packStatisticDao.addInPackStatistic(packSt);
    }

    @Override
    public int addInPackStatistics(List<PackSt> packSt) {
        return packStatisticDao.addInPackStatistics(packSt);
    }

    @Override
    public int addOneOutPackStatistic(PackSt packSt) {
        return packStatisticDao.addOutPackStatistic(packSt);
    }

    @Override
    public int addOutPackStatistics(List<PackSt> packSt) {
        return packStatisticDao.addOutPackStatistics(packSt);
    }

    @Override
    public Map<String,Object> getStatisticByStation(int stationId, int part) {
        Map<String,Object> map = new HashMap<>();
        map.put("InNumber",packStatisticDao.getInStatisticByStation(stationId,part));
        map.put("OutNumber",packStatisticDao.getOutStatisticByStation(stationId,part));
        List<TimeMap> list = packageDao.getPackInStatisticDaliy(stationId);
        map.put("InDistribution", SmoothUtil.timeMapDaily(list));
        return map;
    }

    @Override
    public List<PackSt> getStatisticByStationAndCompany(int stationId, int part) {
        return packStatisticDao.getInStatisticByStationAndCompany(stationId,part);
    }

    @Override
    public List<PackSt> getOutStatisticByStation(int stationId, int part) {
        return packStatisticDao.getOutStatisticByStation(stationId,part);
    }

    @Override
    public List<PackSt> getOutStatisticByStationAndCompany(int stationId, int part) {
        return packStatisticDao.getOutStatisticByStationAndCompany(stationId,part);
    }
}
