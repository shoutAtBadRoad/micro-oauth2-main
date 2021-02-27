package com.post.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.post.db.entities.Pack;
import com.post.db.entities.Smap;
import com.post.db.entity.ImageByte;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PackageDao{

    int addOnePack(Pack pack);

    List<Pack> getPacks();

    int putPackIn(String packId,int shelfId,int stationId,int company,String curtime);

    int updateOnePack(Pack pack);

    Pack getOnePackById(@Param("id") Integer id);

    List<Smap> getPackNumberByCompanyAndArea(@Param("areaId")String areaId);

    Integer getPackNumber(@Param("areaId")String areaId,@Param("status")int status);

    List<Pack> getPackListByMobile(@Param("mobile")String mobile);

    List<Pack> getPackListByMobileAndStation(@Param("stationId")int stationId,@Param("mobile")String mobile);

    List<Pack> getPackListByStation(@Param("stationId")int staionId);

    List<Pack> getPackListByStatus(@Param("status")int status);

    int addImage(@Param("packId") String packId,@Param("image") byte[] image);

    ImageByte getImage(@Param("packId")String packId);

}
