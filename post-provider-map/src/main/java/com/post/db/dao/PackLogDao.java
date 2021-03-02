package com.post.db.dao;

import com.post.db.entities.PackSt;
import com.post.db.entities.PackLog;
import com.post.db.entities.PackStored;
import com.post.db.entities.Smap;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PackLogDao {
    int addInLog(PackStored log);

    PackLog getOneInLogById(int id);

    PackStored getOneInLogByPackId(String packId);

    List<PackLog> getInLogByTime(@Param("part") int part);

    List<PackLog> getAllInLog();

    int addOutLog(PackStored log);

    int getPackageInNumber(@Param("stationId") int stationId,@Param("part") int part);

    // 根据不同快递公司，统计其今天的入库件数
    List<PackSt> getPackInStatistic();

    // 根据不同快递公司，统计其今天的出库件数
    List<PackSt> getPackOutStatistic();

    PackLog getOneOutLogByPackId(@Param("packId") int packId);

    List<PackLog> getOutLogByTime(@Param("part") int part);

    List<PackLog> getAllOutLog();

    int getPackageOutNumber(@Param("stationId") int stationId,@Param("part") int part);

    int getPackInByArea(@Param("areaId") String areaId);

    int getPackOutByArea(@Param("areaId")String areaId);

    // 返回今日，驿站的入库总量
    int getInCountToday(@Param("stationId")int stationId);

    // 返回今日，驿站的出库总量
    int getOutCountToday(@Param("stationId")int stationId);




}
