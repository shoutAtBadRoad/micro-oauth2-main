package com.post.db.service;

import com.github.pagehelper.PageInfo;
import com.post.db.entities.Pack;
import com.post.db.entities.PackSt;
import com.post.db.entity.QueryInfo;
import com.post.db.own.entity.QueryId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PackService {
    int putPackageIn(String packId,int shelfId,int Company);
    int getPackageInNumber(int stationId,int part);

    List<PackSt> getPackInStatisticDaily();
    List<PackSt> getPackOutStatisticDaily();

    List<Pack> getPackListByMobile(String mobile);

    PageInfo<Pack> getPackListByStation(QueryId queryId);

    PageInfo<Pack> getPackListByMobile(QueryId queryId,String mobile);

    List<Pack> getPackListByStation(int staionId);
}
