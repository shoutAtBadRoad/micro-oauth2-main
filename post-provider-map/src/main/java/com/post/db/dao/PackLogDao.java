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
    public int addInLog(PackStored log);

    public PackLog getOneInLogById(int id);

    public PackStored getOneInLogByPackId(String packId);

    public List<PackLog> getInLogByTime(@Param("part") int part);

    public List<PackLog> getAllInLog();

    public int addOutLog(PackLog log);

    public int getPackageInNumber(@Param("stationId") int stationId,@Param("part") int part);

    public List<PackSt> getPackInStatistic();

    public PackLog getOneOutLogByPackId(@Param("packId") int packId);

    public List<PackLog> getOutLogByTime(@Param("part") int part);

    public List<PackLog> getAllOutLog();

    public int getPackageOutNumber(@Param("stationId") int stationId,@Param("part") int part);

    public List<PackSt> getPackOutStatistic();

    public int getPackInByArea(@Param("areaId") String areaId);

    public int getPackOutByArea(@Param("areaId")String areaId);


}
