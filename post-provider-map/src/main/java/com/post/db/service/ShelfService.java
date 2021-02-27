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
//    public int addOneShelf(Shelf shelf);
//    public int updateOneShelf(Shelf shelf);
//    public int putOnePackById(int id);
//    public int getOnePackById(int id);
//    public int getStationByShelf(int shelfId);
//    public int updateShelfStock(List<Shelf> list);
//    public Shelf getOneShelfById(@Param("id") int id);
    PageInfo<Shelf> getShelfByStation(QueryId queryId);

    List<Shelf> getShelfByStation(int stationId);
//    public List<Shelf> getAllShelf();
//    public List<ShelfCount> countShelfByStation();
//    public List<ShelfCountByCompany> countShelfByCompany(@Param("statusId") int status, @Param("part")int part);

}
