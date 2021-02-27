package com.post.db.service;

import com.post.db.dao.StationDao;
import com.post.db.entities.Count;
import com.post.db.entities.Station;
import com.post.db.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StationServiceImpl implements StationService{

    @Resource
    private StationDao stationDao;

    @Override
    public int addOneStation(Station station) {
        return stationDao.addOneStation(station);
    }

    @Override
    public Station getStationById(int id) {
        return stationDao.getStationById(id);
    }

    @Override
    public List<Station> getStationByGeo(String address) {
        return stationDao.getStationByGeo(address);
    }

    @Override
    public int removeStationByID(int id) {
        return stationDao.removeStationByID(id);
    }

    @Override
    public int countStation(String areaId) {
        return stationDao.countStation(areaId);
    }

    @Override
    public List<Count> getCount() {
        return stationDao.getCount();
    }

    @Override
    public List<Count> getCityCount() {
        return stationDao.getCityCount();
    }

    @Override
    public List<Count> getProvinceCount() {
        return stationDao.getProvinceCount();
    }

    @Override
    public int addCount(List<Count> count) {
        return stationDao.addCount(count);
    }
}
