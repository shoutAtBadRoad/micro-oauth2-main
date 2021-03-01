package com.post.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.post.db.entities.Pack;
import com.post.db.entities.Smap;
import com.post.db.entities.TimeMap;
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

    /*
        根据手机号获取快递列表
    */
    List<Pack> getPackListByMobile(@Param("mobile")String mobile);

    /*
        根据手机号和驿站号获取快递列表
     */
    List<Pack> getPackListByMobileAndStation(@Param("stationId")int stationId,@Param("mobile")String mobile);

    /*
        根据驿站号获取快递列表
     */
    List<Pack> getPackListByStation(@Param("stationId")int staionId);

    /*
        根据快递状态获取快递列表
     */
    List<Pack> getPackListByStatus(@Param("status")int status);

    /*
        将快递的图片字节流存入数据库
     */
    int addImage(@Param("packId") String packId,@Param("image") byte[] image);

    /*
        根据快递单号读出实物图的字节数组
     */
    ImageByte getImage(@Param("packId")String packId);

    /*
        统计当天快递的入库时间段分布
     */
    List<TimeMap> getPackInStatisticDaliy(@Param("stationId") int stationId);

}
