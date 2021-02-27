package com.post.db.service;

import com.post.db.entity.QueryInfo;

import java.util.Map;

public interface ShelfStatisticService {

    Map<String,Object> getShelfInfoByStation(int stationId);

}
