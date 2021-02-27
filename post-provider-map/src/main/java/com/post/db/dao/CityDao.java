package com.post.db.dao;

import com.post.db.entities.City;
import com.post.db.entities.CityDistance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CityDao {
    public String getCityName(@Param("id") String CityId);

    public List<City> getCityList();

    int addCityDistance(@Param("cityDistances") List<CityDistance> cityDistances);
}
