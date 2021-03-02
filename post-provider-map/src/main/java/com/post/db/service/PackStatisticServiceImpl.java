package com.post.db.service;

import com.post.db.dao.PackStatisticDao;
import com.post.db.dao.PackageDao;
import com.post.db.dao.ShelfDao;
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
    private ShelfDao shelfDao;

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
        //装入part天内，每天的入库出库数量
        map.put("InNumber",packStatisticDao.getInStatisticByStation(stationId,part));
        map.put("OutNumber",packStatisticDao.getOutStatisticByStation(stationId,part));
//        List<TimeMap> list = packageDao.getPackInStatisticDaliy(stationId);
//        map.put("InDistribution", SmoothUtil.timeMapDaily(list));
        //装入近24小时之内的入库、上架、取件分布
        map.put("InDistribution",packStatisticDao.InDaily(stationId));
        map.put("OnDistribution",packStatisticDao.OnDaily(stationId));
        map.put("OffDistribution",packStatisticDao.OffDaily(stationId));
        //装入驿站最大负载容量与当前负载量
        map.put("MaxCapacityAndCurrent",shelfDao.countShelfByStation(stationId));
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
