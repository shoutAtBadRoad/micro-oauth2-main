package com.yt.core;

import com.yt.entity.Pack;
import com.yt.entity.Station;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service(value = "ytStatistic")
public class YtStatisticImpl implements YtStatistic{

    private ConcurrentHashMap<String, Station> map = new ConcurrentHashMap<String,Station>();

    private HashMap<String,HashMap<String,Integer>> dist = new HashMap<>();


    /**
     * dist初始化
     */
    @PostConstruct
    @Override
    public void initialize() {
        map.put("100",new Station("100"));
    }

    @Override
    public void compute(List<Pack> packs) {
        Date time = new Date();
        for(Pack pack : packs) {
            int minutes = TimeUtils.deal(time,pack.getCanvassTime());
            String canvasCode = pack.getCanvassOrgCode();
            String deliveryCode = pack.getDeliveryOrgCode();
            int valid = dist.get(canvasCode).get(deliveryCode) - minutes;
            if(valid > 1440){
                return;
            }
            if(map.containsKey(deliveryCode)){
                Station station = map.get(deliveryCode);
                Date from = station.getTime();
                if(valid<=4*60){
                    station.ThreeAsc();
                }else if(valid <= 12*60){
                    station.TwoAsc();
                }else {
                    station.OneAsc();
                }
                Long num = station.cwList.get(valid);
                map.get(deliveryCode).cwList.set(valid,num+1);
            }else{
                Station station = new Station(deliveryCode);
                List<Long> list = new ArrayList<>();
                for(int i=0;i<1440;i++){
                    list.add(0L);
                }
                station.cwList.addAll(list);
                station.setTime(new Date());
                station.cwList.set(valid,1L);
                if(valid<=4*60){
                    station.ThreeAsc();
                }else if(valid <= 12*60){
                    station.TwoAsc();
                }else {
                    station.OneAsc();
                }
                map.put(deliveryCode,station);
            }
        }

    }

    @Override
    public List<Long> getCount(String code) {
        List<Long> list = new ArrayList<>();
        Station station = map.get(code);
        list.add(station.getFour());
        list.add(station.getTwelve());
        list.add(station.getTFour());
        return list;
    }

    /**
     * 清理过期数据
     */
    @Override
    public void refresh() {
        System.out.println("*******refresh**********");
    }


}
