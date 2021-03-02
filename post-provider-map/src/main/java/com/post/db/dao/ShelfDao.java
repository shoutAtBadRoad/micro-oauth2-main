package com.post.db.dao;

import com.post.db.entities.Shelf;
import com.post.db.entities.ShelfCount;
import com.post.db.entities.ShelfCountByCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShelfDao {
    public int addOneShelf(Shelf shelf);

    public int updateOneShelf(Shelf shelf);

    public int putOnePackById(int id);

    public int getOnePackById(int id);

    // 改变某驿站下，编号为no货架的库存量
    int changePackByShelf(@Param("station") int station,@Param("no") String no,@Param("value")int value);

    public int getStationByShelf(int shelfId);

    public int updateShelfStock(List<Shelf> list);

    public Shelf getOneShelfById(@Param("id") int id);

    // 查询某驿站内，所有货架的信息
    public List<Shelf> getShelfByStation(@Param("id") int stationId);

    // 查询某驿站内，所有货架的ID
    public List<Integer> getShelfIdByStation(@Param("id")int stationId);

    // 查询所有货架
    public List<Shelf> getAllShelf();

    // 查询驿站的最大容量负载、当前负载量
    public ShelfCount countShelfByStation(@Param("stationId")int stationId);

    public List<ShelfCountByCompany> countShelfByCompany(@Param("statusId") int status,@Param("part")int part);

}
