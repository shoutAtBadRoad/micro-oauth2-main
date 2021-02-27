package com.post.db.service;

import com.post.db.dao.CompanyDao;
import com.post.db.dao.ShelfDao;
import com.post.db.dao.ShelfStatisticDao;
import com.post.db.entities.SList;
import com.post.db.entities.Shelf;
import com.post.db.entities.ShelfSt;
import com.post.db.entities.Smap;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShelfStatisticServiceImpl implements ShelfStatisticService{
    @Resource
    private ShelfStatisticDao shelfStatisticDao;
    @Resource
    private ShelfDao shelfDao;
    @Resource
    private CompanyDao companyDao;

    @Override
    public Map<String, Object> getShelfInfoByStation(int stationId) {
        Map<String,Object> map = new HashMap<>();
        //装入货架的快件分布
        //获取15天内货架的上架情况,首先获得站内所有货架的id,再获取各货架的入库情况
        List<Integer> shelfId = shelfDao.getShelfIdByStation(stationId);
        List<ShelfSt> shelfSts = shelfStatisticDao.getInShelfStatistic(stationId,15);
        List<SList> sLists = new ArrayList<>();
        for(int i: shelfId){
            SList sList = new SList();
            sList.setId(i);
            sList.setData(new int[]{0,0,0,0,0,0,0,0});
            sLists.add(sList);
        }
        for(ShelfSt s : shelfSts){
            for(SList k : sLists){
                if(k.getId()==s.getShelfId()){
                    int[] data = k.getData();
                    data[Integer.parseInt(s.getCompany())-1]=s.getNumber();
                    break;
                }
            }
        }
        map.put("shelfInCompanyIN15Day",sLists);
        //获取15天内货架的取件情况
        shelfSts = shelfStatisticDao.getOutShelfStatistic(stationId,15);
        List<SList> sLists1 = new ArrayList<>();
        for(int i: shelfId){
            SList sList = new SList();
            sList.setId(i);
            sList.setData(new int[]{0,0,0,0,0,0,0,0});
            sLists1.add(sList);
        }
        for(ShelfSt s : shelfSts){
            for(SList k : sLists1){
                if(k.getId()==s.getShelfId()){
                    int[] data = k.getData();
                    data[Integer.parseInt(s.getCompany())-1]=s.getNumber();
                    break;
                }
            }
        }
        map.put("shelfOutCompanyIN15Day",sLists);
        //添加快递公司映射表
        List<Smap> list = companyDao.getCompanyList();
        List<String> list1 = new ArrayList<>();
        for(Smap s : list){
            list1.add(s.getName());
        }
        map.put("company",list1);
        return map;
    }
}
