package com.post.db.service;

import com.post.db.entities.PackSt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PackStatisticService {
    public int addOneInPackStatistic(PackSt packSt);
    public int addInPackStatistics(@Param("recivePack") List<PackSt> packSt);
    public int addOneOutPackStatistic(PackSt packSt);
    public int addOutPackStatistics(@Param("recivePack") List<PackSt> packSt);
    public List<PackSt> getStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    public List<PackSt> getStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

    public List<PackSt> getOutStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    public List<PackSt> getOutStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

}
