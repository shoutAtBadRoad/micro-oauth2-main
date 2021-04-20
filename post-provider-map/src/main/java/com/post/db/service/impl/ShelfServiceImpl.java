package com.post.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.post.db.controller.ShelfController;
import com.post.db.dao.ShelfDao;
import com.post.db.entities.Shelf;
import com.post.db.entity.QueryInfo;
import com.post.db.own.entity.QueryId;
import com.post.db.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShelfServiceImpl implements ShelfService {
    @Resource
    private ShelfDao shelfDao;

    @Override
    public PageInfo<Shelf> getShelfByStation(QueryId queryId) {
        PageHelper.startPage(queryId.getPagenum(),queryId.getPagesize());
        List<Shelf> shelf = shelfDao.getShelfByStation(queryId.getId());
        PageInfo<Shelf> pageInfo = new PageInfo<>(shelf);
        return pageInfo;
    }

    @Override
    public List<Shelf> getShelfByStation(int stationId) {
        return shelfDao.getShelfByStation(stationId);
    }

    /**
     * 获得当前驿站内 货架的使用率
     * @param stationId
     * @return
     */
    @Override
    public List<Float> getUseRate(int stationId) {
        List<Shelf> shelves = shelfDao.getShelfByStation(stationId);
        List<Float> useRate = new ArrayList<>();
        for(Shelf shelf : shelves){
            useRate.add(((float)shelf.getStock()/shelf.getCapacity()));
        }
        log.info("驿站"+stationId+"的货架的使用率为："+useRate.toString());
        return useRate;
    }
}
