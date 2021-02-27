package com.post.db.dao;

import com.post.db.entities.PackSend;
import com.post.db.entities.Smap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PackSendLogDao {

    int addOnePack(PackSend pack);

    List<PackSend> getPacksByTerminal(@Param("terminal")long terminal);

    List<Smap> getPackNotArrived(@Param("terminal")int terminal,@Param("areaId")String areaId);

}
