package com.post.db.dao;

import com.post.db.entities.PackSt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PackStatisticDao {
    public int addInPackStatistic(PackSt packSt);
    public int addInPackStatistics(@Param("packSt") List<PackSt> packSt);
    public List<PackSt> getInStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    public List<PackSt> getInStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

    public int addOutPackStatistic(PackSt packSt);
    public int addOutPackStatistics(@Param("packSt") List<PackSt> packSt);
    public List<PackSt> getOutStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    public List<PackSt> getOutStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

    public List<Integer> getInStatisticByArea(@Param("areaId")String areaId,@Param("part")int part);
    public List<Integer> getOutStatisticByArea(@Param("areaId")String areaId,@Param("part")int part);

}
