package com.post.db.service;

import com.post.db.entities.Count;
import com.post.db.entities.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationService {
    public int addOneStation(Station station);

    public Station getStationById(@Param("id") int id);

    public List<Station> getStationByGeo(String address);

    public int removeStationByID(@Param("id")int id);

    public int countStation(@Param("areaId")String areaId);

    public List<Count> getCount();

    public List<Count> getCityCount();

    public List<Count> getProvinceCount();

    public int addCount(@Param("count") List<Count> count);
}
