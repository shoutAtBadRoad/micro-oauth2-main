package com.post.db.service;

import com.post.db.entities.PackSt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PackStatisticService {
    int addOneInPackStatistic(PackSt packSt);
    int addInPackStatistics(@Param("recivePack") List<PackSt> packSt);
    int addOneOutPackStatistic(PackSt packSt);
    int addOutPackStatistics(@Param("recivePack") List<PackSt> packSt);


    Map<String,Object> getStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    List<PackSt> getStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

    List<PackSt> getOutStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    List<PackSt> getOutStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

}
