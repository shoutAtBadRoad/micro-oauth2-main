package com.post.db.service.impl;

import com.post.db.dao.*;
import com.post.db.entities.Location;
import com.post.db.entities.Smap;
import com.post.db.entities.Station;
import com.post.db.service.IndexService;
import com.post.db.service.StationService;
import com.post.db.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {
    @Resource
    private StationService stationService;
    @Resource
    private PackLogDao packLogDao;
    @Resource
    private PackStatisticDao packStatisticDao;
    @Resource
    private PackageDao packageDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private StationDao stationDao;

    /*
        封装主页所需要的数据
     */
    @Override
    public Map<String, Object> getIndexData(String areaId) {
        String id = StringUtils.cutZero(areaId);
        Map<String,Object> map = new HashMap<>();
        //装入驿站个数
        map.put("stationNumber",stationService.countStation(id));
        //装入今日到件数
        map.put("todayInNumber",packLogDao.getPackInByArea(id));
        //装入今日出库数
        map.put("todayOutNumber",packLogDao.getPackOutByArea(id));
        /**
         * 这块数据先模拟
         */
        //装入近7天的入库数,暂时没有足够数据，为了有数据先设为70天内
        List<Integer> day7InNumber = packStatisticDao.getInStatisticByArea(id,70);
        List<Integer> lastYearNumber = packStatisticDao.getOutStatisticByArea(id,70);
        for(int i=0;i<7;i++){
            day7InNumber.set(i, new Random().nextInt(100));
            lastYearNumber.set(i, new Random().nextInt(100));
        }
        map.put("day7InNumber",day7InNumber);
        //装入近7天的出库数
        map.put("lastYearNumber",lastYearNumber);
        //装入各快递公司的快件总量
        List<Smap> list = packageDao.getPackNumberByCompanyAndArea(id);
        List<Smap> elist = companyDao.getCompanyList();
        list = fillUp(list,elist);
        map.put("companyNumber",list);
        //装入在库量，已取件
        map.put("stockNumber",packageDao.getPackNumber(id,101));
        map.put("pickupNumber",packageDao.getPackNumber(id,102));
        //装入问题件，后续再加
        /*????????????????????????????????????????????????????*/
        //如果请求县区数据，需要装入县区的驿站信息
        if(id.length()==6){
            List<Station> stations = stationDao.getStationByArea(id);
            map.put("stations",stations);
        }
        return map;
    }

    @Override
    public List<List<Double>> getStationLocation() {
        List<Location> locations = stationDao.getAllStationLocation();
        List<List<Double>> lists = new ArrayList<>();
        for(Location l : locations){
            List<Double> d = new ArrayList<>();
//            d.add((double)l.getId());
            d.add(l.getLongtitude());
            d.add(l.getLatitude());
            lists.add(d);
        }
        return lists;
    }

    public List<Smap> fillUp(List<Smap> list,List<Smap> elist){
        int flag = 0;
        for(Smap s : elist){
            flag=0;
            for(Smap l : list){
                if(s.getName().equals(l.getName())){
                 flag=1;
                }
            }
            if(flag==0){
                list.add(new Smap(s.getName(),0));
            }
        }
        return list;
    }
}
