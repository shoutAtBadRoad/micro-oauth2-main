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

    int changePackByShelf(@Param("station") int station,@Param("no") String no,@Param("value")int value);

    public int getStationByShelf(int shelfId);

    public int updateShelfStock(List<Shelf> list);

    public Shelf getOneShelfById(@Param("id") int id);

    public List<Shelf> getShelfByStation(@Param("id") int stationId);

    public List<Integer> getShelfIdByStation(@Param("id")int stationId);

    public List<Shelf> getAllShelf();

    public List<ShelfCount> countShelfByStation();

    public List<ShelfCountByCompany> countShelfByCompany(@Param("statusId") int status,@Param("part")int part);

}
