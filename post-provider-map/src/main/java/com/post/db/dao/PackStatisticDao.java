package com.post.db.dao;

import com.post.db.entities.PackSt;
import com.post.db.entities.TimeMap;
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

    // 添加一条每日统计信息
    public int addOutPackStatistic(PackSt packSt);
    // 批量添加每日统计信息（记录驿站的各种快递公司快递的出库量）
    public int addOutPackStatistics(@Param("packSt") List<PackSt> packSt);

    // 查询某驿站近part天内，出库量（区分与不区分各快递公司）
    public List<PackSt> getOutStatisticByStation(@Param("stationId")int stationId, @Param("part")int part);
    public List<PackSt> getOutStatisticByStationAndCompany(@Param("stationId")int stationId, @Param("part")int part);

    // 查询近part天内，某地区的快递进出总数量
    public List<Integer> getInStatisticByArea(@Param("areaId")String areaId,@Param("part")int part);
    public List<Integer> getOutStatisticByArea(@Param("areaId")String areaId,@Param("part")int part);

    // 查询驿站近24小时的入库情况
    List<TimeMap> InDaily(@Param("stationId")int stationId);

    // 查询驿站近24小时的上架情况
    List<TimeMap> OnDaily(@Param("stationId")int stationId);

    // 查询驿站近24小时的取件情况
    List<TimeMap> OffDaily(@Param("stationId")int stationId);

}
