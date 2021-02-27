package com.post.db.bean;

import com.post.db.dao.AreaDao;
import com.post.db.dao.CityDao;
import com.post.db.dao.ProvinceDao;
import com.post.db.entities.*;
import com.post.db.service.StationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationCountCache implements CommandLineRunner{//implements CommandLineRunner
    private  Map<String,Integer> countArea = new HashMap<>();
    private  Map<String,Integer> countCity = new HashMap<>();
    private  Map<String,Integer> countProvince = new HashMap<>();
    private  Integer count = 0;

    @Resource
    private StationService stationService;
    @Resource
    private AreaDao areaDao;
    @Resource
    private CityDao cityDao;
    @Resource
    private ProvinceDao provinceDao;

//    @Override
//    public void run(String... args) throws Exception {
//        List<Area> areas = areaDao.getAreaList();
//        List<City> cities = cityDao.getCityList();
//        List<Province> provinces = provinceDao.getProvinceList();
//        for(Province province : provinces){
//            this.countProvince.put(province.getProvinceId(),0);
//        }
//        for(City city : cities){
//            this.countCity.put(city.getCityId(),0);
//        }
//        for(Area area : areas) {
//            int c = stationService.countStation(area.getAreaId());
//            this.countArea.put(area.getAreaId(),c);
//            c += this.countCity.get(area.getCityId());
//            this.countCity.remove(area.getCityId());
//            this.countCity.put(area.getCityId(),c);
//        }
//        for(City city : cities){
//            int t = this.countCity.get(city.getCityId())+countProvince.get(city.getProvinceId());
//            this.countProvince.remove(city.getProvinceId());
//            this.countProvince.put(city.getProvinceId(),t);
//        }
//        for(Province province : provinces){
//            this.count += this.countProvince.get(province.getProvinceId());
//            System.out.println(this.countProvince.get(province.getProvinceId()));
//        }
//    }

    public Integer getCount(String Id) throws Exception {
//        String[] keys =  countArea.keySet().toArray(new String[0]);
//        Random random = new Random();
//        for(int i=0;i<10000;i++){
//            Station station = new Station();
//            station.setName("菜鸟驿站"+i+"店");
//            station.setCapacity(1000);
//            station.setAreaId(keys[random.nextInt(keys.length)]);
//            stationService.addOneStation(station);
//        }
//        this.run();
        if(Id.length()<6){
            StringBuilder IdBuilder = new StringBuilder(Id);
            for(int i = 0; i<6- IdBuilder.length();){
                IdBuilder.append('0');
            }
            Id = IdBuilder.toString();
        }
        Integer province = Integer.valueOf(Id.substring(0,2));
        Integer city = Integer.valueOf(Id.substring(2,4));
        Integer area = Integer.valueOf(Id.substring(4,6));
        if(province==0){
            return this.count;
        }else if(city==0 && area==0){
            //查询省个数
            if(this.countProvince.containsKey(Id)) {
                return countProvince.get(Id);
            }
            else {
                return -1;
            }

        }else if(area==0){
            //查询市个数
            if(this.countCity.containsKey(Id)){
                return countCity.get(Id);
            }
            else {
                return -1;
            }
    }else {
            //查询县区个数
            if(this.countArea.containsKey(Id)) {
                return countArea.get(Id);
            }else {
                return -1;
            }
        }
    }

    /*
        编写数据库sql统计，结果表count_station
     */
    public void countStation(){
        int c = 0;
        List<Count> list = stationService.getCount();
        stationService.addCount(list);
        List<Count> list1 = stationService.getCityCount();
        List<Count> list2 = stationService.getProvinceCount();
//        for(Count count : list){
//            System.out.println(count.getAreaId()+":"+count.getNumber());
//        }
        for(Count count : list1){
            count.setAreaId(count.getAreaId()+"00");
        }
        for(Count count : list2){
            c+=count.getNumber();
            count.setAreaId(count.getAreaId()+"0000");
        }
        System.out.println(c);
        list2.add(new Count("000000",c));
        stationService.addCount(list1);
        stationService.addCount(list2);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
