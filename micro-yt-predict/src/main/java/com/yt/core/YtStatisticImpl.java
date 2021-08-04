package com.yt.core;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.yt.entity.Pack;
import com.yt.entity.Station;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service(value = "ytStatistic")
public class YtStatisticImpl implements YtStatistic{

    private final ConcurrentHashMap<String, Station> map = new ConcurrentHashMap<String,Station>();

    private final HashMap<String,Map<String,Integer>> dist = new HashMap<>();

    private volatile boolean sk;

    /**
     * dist初始化
     */
    @PostConstruct
    @Override
    public void initialize() {
//        map.put("100",new Station("100"));
        sk = true;
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put("100",1440);
        dist.put("1",tmp);
    }

    @Override
    public void compute(List<Pack> packs) {
        Date time = new Date();
        for(Pack pack : packs) {
//            int minutes = TimeUtils.deal(time,pack.getCanvassTime());
            int minutes = (int) DateUtil.between(pack.getCanvassTime(),time, DateUnit.MINUTE);
            String canvasCode = pack.getCanvassOrgCode();
            String deliveryCode = pack.getDeliveryOrgCode();
            int valid = dist.get(canvasCode).get(deliveryCode) - minutes;
            if(valid >= 1440){
                return;
            }
            if(map.containsKey(deliveryCode)){
                Station station = map.get(deliveryCode);
                Date from = station.getTime();
                if(valid<=4*60){
                    station.ThreeAsc();
                }else if(valid <= 12*60){
                    station.TwoAsc();
//                    System.out.println("12hours内");
                }else {
                    station.OneAsc();
                }
                Integer num = station.cwList.get(valid);
                map.get(deliveryCode).cwList.set(valid,num+1);
            }else{
                Station station = new Station(deliveryCode);
                List<Integer> list = new ArrayList<>();
                for(int i=0;i<1440;i++){
                    list.add(0);
                }
                station.cwList.addAll(list);
                station.setTime(new Date());
                station.cwList.set(valid,1);
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
    public List<Integer> getCount(String code) {
        List<Integer> list = new ArrayList<>();
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
        ConcurrentHashMap.KeySetView<String, Station> strings = map.keySet();
        for(String s : strings){

            Integer aLong = map.get(s).cwList.get(0);
            boolean f = false;
            if(aLong >0)
                f = true;
            map.get(s).cwList.add(0);
            map.get(s).cwList.remove(0);

            Integer two = map.get(s).cwList.get(4*60);
            Integer three = map.get(s).cwList.get(12*60);

            map.get(s).setFour(map.get(s).getFour()-aLong+two);
            map.get(s).setTwelve(map.get(s).getTwelve()-aLong-two+three);
            map.get(s).setTFour(map.get(s).getTFour()-aLong-three);
            if(f)
                System.out.println("success");
        }
//        System.out.println("*********end************");
    }

    public boolean getSk(){
        return sk;
    }

    public void trans(){
        sk = !sk;
    }

}
