package com.post.db.dao;

import com.post.db.entities.PackSt;
import com.post.db.entities.PackLog;
import com.post.db.entities.PackStored;
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

    int addOutLog(PackLog log);

    int getPackageInNumber(@Param("stationId") int stationId,@Param("part") int part);

    List<PackSt> getPackInStatistic();

    PackLog getOneOutLogByPackId(@Param("packId") int packId);

    List<PackLog> getOutLogByTime(@Param("part") int part);

    List<PackLog> getAllOutLog();

    int getPackageOutNumber(@Param("stationId") int stationId,@Param("part") int part);

    List<PackSt> getPackOutStatistic();

    int getPackInByArea(@Param("areaId") String areaId);

    int getPackOutByArea(@Param("areaId")String areaId);


}
