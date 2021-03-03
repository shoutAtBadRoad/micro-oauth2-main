package com.post.db.dao;

import com.post.db.entities.ShelfSt;
import com.post.db.entities.Smap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShelfStatisticDao {
    //统计驿站内所有货架快递情况
    List<ShelfSt> getCurShelfInfo(@Param("stationId")int stationId,@Param("status")int status);
    //统计货架快递数量情况
    List<Smap> getCurShelfInfoByShelf(@Param("shelfId")int shelfId,@Param("status")int status);


    //只统计到今天0点为止的时间段数据，今天白天发生的数据不统计
    List<ShelfSt> getInShelfStatistic(@Param("stationId")int stationId,@Param("part")int part);
    //精确到现在时间的时间段
    List<ShelfSt> getInShelfStatisticArc(@Param("stationId")int stationId,@Param("part")int part);

    //只统计到今天0点为止的时间段数据，今天白天发生的数据不统计
    List<ShelfSt> getOutShelfStatistic(@Param("stationId")int stationId,@Param("part")int part);
    //精确到现在时间的时间端
    List<ShelfSt> getOutShelfStatisticArc(@Param("stationId")int stationId,@Param("part")int part);
}
