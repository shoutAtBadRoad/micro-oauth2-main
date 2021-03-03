package com.post.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.post.db.controller.ShelfController;
import com.post.db.dao.ShelfDao;
import com.post.db.entities.Shelf;
import com.post.db.entity.QueryInfo;
import com.post.db.own.entity.QueryId;
import com.post.db.service.ShelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
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
}
