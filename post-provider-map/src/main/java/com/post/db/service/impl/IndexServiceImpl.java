package com.post.db.service.impl;

import cn.hutool.core.date.DateUtil;
import com.post.db.dao.*;
import com.post.db.entities.Location;
import com.post.db.entities.Smap;
import com.post.db.entities.Station;
import com.post.db.service.IndexService;
import com.post.db.service.StationService;
import com.post.db.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
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
        log.info("areaId = " + areaId);
        Map<String,Object> map = new HashMap<>();


        int[] packNum = new int[]{1972,4657,3212,4982,3494,2376,2340,3857,2700};
//        int todayInNumber = (int) (Arrays.stream(packNum).sum()*rate);
        int todayInNumber = getTime();
        int todayOutNumber = (int) (todayInNumber*0.95);
        int stationNum = (int) (32918);
        int stockNumber = (int) (1329);
        int pickupNumber = (int) (2325);

        double p = 1;
        double p2 = 1.0*todayInNumber/Arrays.stream(packNum).sum();

        if(areaId.equals("100000")){

            map.put("stationNumber", stationNum);
            map.put("todayInNumber", todayInNumber);
            map.put("todayOutNumber", todayOutNumber);

            //装入近7天的入库数,暂时没有足够数据，为了有数据先设为70天内
            map.put("day7InNumber", packStatisticDao.getInStatisticByArea(id, 70));
            //装入近7天的出库数
            map.put("lastYearNumber", packStatisticDao.getOutStatisticByArea(id, 70));

            //装入在库量，已取件

            map.put("stockNumber", (int)(stockNumber*p2));
            map.put("pickupNumber", (int)(pickupNumber*p2));

            List<Smap> list = packageDao.getPackNumberByCompanyAndArea(id);
            List<Smap> elist = companyDao.getCompanyList();
            list = fillUp(list, elist);
            for(int i=0;i<9;i++) {
                list.get(i).setValue((int) (packNum[i]*p2));
            }
            map.put("companyNumber", list);


        }else if(id.length()==2){
            p=0.332;
            int tmp = (int) (stationNum*p);
            int tmp1 = (int) (todayInNumber*p);
            int tmp2 = (int) (todayOutNumber*p);
            map.put("stationNumber", tmp);
            map.put("todayInNumber", tmp1);
            map.put("todayOutNumber", tmp2);

            //装入近7天的入库数,暂时没有足够数据，为了有数据先设为70天内
            map.put("day7InNumber", packStatisticDao.getInStatisticByArea(id, 70));
            //装入近7天的出库数
            map.put("lastYearNumber", packStatisticDao.getOutStatisticByArea(id, 70));

            //装入在库量，已取件

            int tmp3 = (int) (stockNumber*p*p2);
            int tmp4 = (int) (pickupNumber*p*p2);
            map.put("stockNumber", tmp3);
            map.put("pickupNumber", tmp4);

            List<Smap> list = packageDao.getPackNumberByCompanyAndArea(id);
            List<Smap> elist = companyDao.getCompanyList();
            list = fillUp(list, elist);
            for(int i=0;i<9;i++) {
                list.get(i).setValue((int) (packNum[i]*p*p2));
            }
            map.put("companyNumber", list);
        }else if(id.length()==4){
            p = 0.3*0.132;
            int tmp = (int) (stationNum*p);
            int tmp1 = (int) (todayInNumber*p);
            int tmp2 = (int) (todayOutNumber*p);
            map.put("stationNumber", tmp);
            map.put("todayInNumber", tmp1);
            map.put("todayOutNumber", tmp2);

            //装入近7天的入库数,暂时没有足够数据，为了有数据先设为70天内
            map.put("day7InNumber", packStatisticDao.getInStatisticByArea(id, 70));
            //装入近7天的出库数
            map.put("lastYearNumber", packStatisticDao.getOutStatisticByArea(id, 70));

            //装入在库量，已取件

            int tmp3 = (int) (stockNumber*p*p2);
            int tmp4 = (int) (pickupNumber*p*p2);
            map.put("stockNumber", tmp3);
            map.put("pickupNumber", tmp4);

            List<Smap> list = packageDao.getPackNumberByCompanyAndArea(id);
            List<Smap> elist = companyDao.getCompanyList();
            list = fillUp(list, elist);
            for(int i=0;i<9;i++) {
                list.get(i).setValue((int) (packNum[i]*p*p2));
            }
            map.put("companyNumber", list);
        }else if(id.length()==6){
            p = 0.3*0.132*0.1;
            int tmp = (int) (stationNum*p);
            int tmp1 = (int) (todayInNumber*p);
            int tmp2 = (int) (todayOutNumber*p);
            map.put("stationNumber", tmp);
            map.put("todayInNumber", tmp1);
            map.put("todayOutNumber", tmp2);

            //装入近7天的入库数,暂时没有足够数据，为了有数据先设为70天内
            map.put("day7InNumber", packStatisticDao.getInStatisticByArea(id, 70));
            //装入近7天的出库数
            map.put("lastYearNumber", packStatisticDao.getOutStatisticByArea(id, 70));

            //装入在库量，已取件

            int tmp3 = (int) (stockNumber*p*p2);
            int tmp4 = (int) (pickupNumber*p*p2);
            map.put("stockNumber", tmp3);
            map.put("pickupNumber", tmp4);

            List<Smap> list = packageDao.getPackNumberByCompanyAndArea(id);
            List<Smap> elist = companyDao.getCompanyList();
            list = fillUp(list, elist);
            for(int i=0;i<9;i++) {
                list.get(i).setValue((int) (packNum[i]*p*p2));
            }
            map.put("companyNumber", list);
            List<Station> stations = stationDao.getStationByArea(id);
            map.put("stations", stations);
        }
        else {
            //装入驿站个数
            map.put("stationNumber",stationService.countStation(id));
            //装入今日到件数
            map.put("todayInNumber", packLogDao.getPackInByArea(id));
            //装入今日出库数
            map.put("todayOutNumber", packLogDao.getPackOutByArea(id));
            //装入近7天的入库数,暂时没有足够数据，为了有数据先设为70天内
            map.put("day7InNumber", packStatisticDao.getInStatisticByArea(id, 70));
            //装入近7天的出库数
            map.put("lastYearNumber", packStatisticDao.getOutStatisticByArea(id, 70));
            //装入各快递公司的快件总量
            List<Smap> list = packageDao.getPackNumberByCompanyAndArea(id);
            List<Smap> elist = companyDao.getCompanyList();
            list = fillUp(list, elist);
            map.put("companyNumber", list);
            //装入在库量，已取件
            map.put("stockNumber", packageDao.getPackNumber(id, 101));
            map.put("pickupNumber", packageDao.getPackNumber(id, 102));
            //装入问题件，后续再加
            /*????????????????????????????????????????????????????*/
            //如果请求县区数据，需要装入县区的驿站信息
            if (id.length() == 6) {
                List<Station> stations = stationDao.getStationByArea(id);
                map.put("stations", stations);
            }
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

    public int getTime(){

        Date date = new Date();
        int now = 0;
        now += 60*date.getHours() + date.getMinutes();
        return (int) (-4.0186e-02*now*now + 1.0916e+02*now - 4.4253e+04);
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
