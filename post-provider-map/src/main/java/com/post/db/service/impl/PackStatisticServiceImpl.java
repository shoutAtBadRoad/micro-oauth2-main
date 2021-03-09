package com.post.db.service.impl;

import com.post.db.bean.RedisCli;
import com.post.db.dao.PackLogDao;
import com.post.db.dao.PackStatisticDao;
import com.post.db.dao.PackageDao;
import com.post.db.dao.ShelfDao;
import com.post.db.entities.PackLog;
import com.post.db.entities.PackSt;
import com.post.db.entities.TimeMap;
import com.post.db.entity.RedisCat;
import com.post.db.service.PackStatisticService;
import com.post.db.utils.SmoothUtil;
import com.post.db.utils.YSTime;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PackStatisticServiceImpl implements PackStatisticService {
    @Resource
    private PackStatisticDao packStatisticDao;
    @Resource
    private ShelfDao shelfDao;
    @Resource
    private PackLogDao packLogDao;
    @Resource
    private RedisCli redisCli;

    @Override
    public int addOneInPackStatistic(PackSt packSt) {
        return packStatisticDao.addInPackStatistic(packSt);
    }

    @Override
    public int addInPackStatistics(List<PackSt> packSt) {
        return packStatisticDao.addInPackStatistics(packSt);
    }

    @Override
    public int addOneOutPackStatistic(PackSt packSt) {
        return packStatisticDao.addOutPackStatistic(packSt);
    }

    @Override
    public int addOutPackStatistics(List<PackSt> packSt) {
        return packStatisticDao.addOutPackStatistics(packSt);
    }

    @Override
    public Map<String,Object> getStatisticByStation(int stationId, int part){
        Map<String,Object> map = new HashMap<>();
        //装入part天内，每天的入库出库数量
        List<PackSt> inStatisticByStation = packStatisticDao.getInStatisticByStation(stationId, part);
        List<PackSt> outStatisticByStation = packStatisticDao.getOutStatisticByStation(stationId, part);
        List<Integer> inNumber = new ArrayList<>();
        List<Integer> outNumber = new ArrayList<>();
        List<String> curDate = new ArrayList<>();
        for(int i=0;i<inStatisticByStation.size();i++){
            inNumber.add(inStatisticByStation.get(i).getNumber());
            outNumber.add(outStatisticByStation.get(i).getNumber());
            curDate.add(inStatisticByStation.get(i).getCurDate());
        }
        map.put("InNumber",inNumber);
        map.put("OutNumber",outNumber);
        map.put("curDate",curDate);
//        List<TimeMap> list = packageDao.getPackInStatisticDaliy(stationId);
//        map.put("InDistribution", SmoothUtil.timeMapDaily(list));
        //装入近24小时之内的入库、上架、取件分布
        map.put("InDistribution",packStatisticDao.InDaily(stationId));
        map.put("OnDistribution",packStatisticDao.OnDaily(stationId));
        map.put("OffDistribution",packStatisticDao.OffDaily(stationId));
        //装入驿站最大负载容量与当前负载量
        map.put("MaxCapacityAndCurrent",shelfDao.countShelfByStation(stationId));
        //装入今日的入库出库总量
        map.put("InToday",packLogDao.getInCountToday(stationId));
        map.put("OutToday",packLogDao.getOutCountToday(stationId));
        //装入今日揽收件、问题件数量
        map.put("lanshou",12);
        map.put("wenti",2);
        //装入预计到达件数、预计收入
        map.put("predict",356);
        map.put("income",5200);
        //装入最新的取件记录
//        List<PackLog> logs = packLogDao.getLatestOutLogs(1,15);
//        log.info("记录条数："+logs.size());
//        List<List<String>> lists = new ArrayList<>();
//        for(PackLog log:logs){
//            List<String> list = new ArrayList<>();
//            list.add(log.getPackId());
//            list.add(log.getCurDate());
//            lists.add(list);
//        }
//        map.put("latestOutLog",lists);
        long start = System.currentTimeMillis();
        map.put("latestOutLog",getLatestOutLog(stationId,15));
        long end = System.currentTimeMillis();
        log.info("花费时间:"+(end-start)+"ms");
        return map;
    }

    @Override
    public List<PackSt> getStatisticByStationAndCompany(int stationId, int part) {
        return packStatisticDao.getInStatisticByStationAndCompany(stationId,part);
    }

    @Override
    public List<PackSt> getOutStatisticByStation(int stationId, int part) {
        return packStatisticDao.getOutStatisticByStation(stationId,part);
    }

    @Override
    public List<PackSt> getOutStatisticByStationAndCompany(int stationId, int part) {
        return packStatisticDao.getOutStatisticByStationAndCompany(stationId,part);
    }

    public List<List<String>> getLatestOutLog(int stationId,int number){
        String time = redisCli.get(RedisCat.LatestOutTime+stationId);
        List<List<String>> lists = new ArrayList<>();
        boolean b = YSTime.compareNow(time);
        if(time==null || b){
            // redis缓存中没有记录或者记录已经过时了
            // 如果记录过时了，先把旧记录清空
            if(b){
                redisCli.clearList(RedisCat.LatestOutList+stationId);
            }

            log.info("没有相应记录，从数据库读取**********");
            List<PackLog> logs = packLogDao.getLatestOutLogs(1,15);
            log.info("记录条数："+logs.size());
            for(PackLog log:logs){
                List<String> list = new ArrayList<>();
                list.add(log.getPackId());
                list.add(log.getCurDate());
                lists.add(list);
                redisCli.putList(RedisCat.LatestOutList+stationId,list);
            }
            redisCli.set(RedisCat.LatestOutTime+stationId,YSTime.getYMDHMS());
            return lists;
        }else {
            // redis缓存中有对应记录
            log.info("有相应缓存，直接从缓存中拿数据*********");
            return redisCli.getList(RedisCat.LatestOutList+stationId);
        }
    }
}
