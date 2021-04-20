package com.post.db.service;

import com.github.pagehelper.PageInfo;
import com.post.db.controller.ShelfController;
import com.post.db.entities.Shelf;
import com.post.db.entities.ShelfCount;
import com.post.db.entities.ShelfCountByCompany;
import com.post.db.entity.QueryInfo;
import com.post.db.own.entity.QueryId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShelfService {

    /**
     * 分页查询
     * 获得驿站内的货架信息
     * @param queryId
     * @return
     */
    PageInfo<Shelf> getShelfByStation(QueryId queryId);

    /**
     * 不带分页查询
     * 直接获得驿站内所有货架的信息
     * @param stationId
     * @return
     */
    List<Shelf> getShelfByStation(int stationId);

    /**
     * 获得当前货架的使用率
     * @param stationId
     * @return
     */
    List<Float> getUseRate(int stationId);


}
