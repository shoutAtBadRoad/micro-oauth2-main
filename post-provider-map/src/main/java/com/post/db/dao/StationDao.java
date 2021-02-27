package com.post.db.dao;

import com.post.db.entities.Count;
import com.post.db.entities.Location;
import com.post.db.entities.Smap;
import com.post.db.entities.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StationDao {
    public int addOneStation(Station station);

    public Station getStationById(@Param("id") int id);

    public List<Station> getStationByGeo(String address);

    public List<Station> getStationByArea(@Param("areaId") String areaId);

    public List<Location> getAllStationLocation();

    public int removeStationByID(@Param("id")int id);

    public int countStation(@Param("areaId")String areaId);

    public List<Count> getCount();

    public List<Count> getCityCount();

    public List<Count> getProvinceCount();

    public int addCount(@Param("count") List<Count> count);
}
