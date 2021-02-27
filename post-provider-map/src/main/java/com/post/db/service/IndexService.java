package com.post.db.service;

import com.post.db.entities.Location;
import com.post.db.entities.Smap;

import java.util.List;
import java.util.Map;

public interface IndexService {
    public Map<String,Object> getIndexData(String areaId);

    public List<List<Double>> getStationLocation();
}
