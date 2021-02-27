package com.post.db.service;

import com.post.db.dao.PackStatisticDao;
import com.post.db.entities.PackSt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PackStatisticServiceImpl implements PackStatisticService{
    @Resource
    private PackStatisticDao packStatisticDao;
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
    public List<PackSt> getStatisticByStation(int stationId, int part) {
        return packStatisticDao.getInStatisticByStation(stationId,part);
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
