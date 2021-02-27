package com.post.db.service;

import com.post.db.dao.ShelfStatisticDao;
import com.post.db.entities.Shelf;
import com.post.db.entities.ShelfSt;
import com.post.db.entities.Smap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StationInfoServiceImpl implements StationInfoService{

    @Resource
    //货架数据统计获取
    private ShelfStatisticService shelfStatisticService;
    @Resource
    //货架统计数据mapper
    private ShelfStatisticDao shelfStatisticDao;
    @Resource
    //货架信息直接获取
    private ShelfService shelfService;
    /*
        封装驿站页面需要的数据
     */
    @Override
    public Map<String, Object> getStationInfoById(int id) {
        //装入每个货架15天内的上架取货情况，并按快递公司分类
        Map<String, Object> shelfInfo = shelfStatisticService.getShelfInfoByStation(id);
        //装入每个货架现有快递情况
        List<ShelfSt> list = shelfStatisticDao.getCurShelfInfo(id);
        shelfInfo.put("curShelfInfo",list);
        //装入驿站内快递公司的快递数量分布
        List<Smap> smaps = shelfStatisticDao.getCurShelfInfoByShelf(id);
        shelfInfo.put("companyShare",smaps);
        //装入驿站内现有的库存量以及剩余容量
        List<Shelf> shelves = shelfService.getShelfByStation(id);
        int stock = shelves.stream().mapToInt(Shelf::getStock).sum();
        int space = shelves.stream().mapToInt(Shelf::getCapacity).sum();
        shelfInfo.put("curStock",stock);
        shelfInfo.put("curSpaceLeft",space);
        //装入
        return shelfInfo;
    }
}
