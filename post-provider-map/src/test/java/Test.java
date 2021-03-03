import com.post.db.MapServiceMain;
import com.post.db.dao.*;
import com.post.db.entities.*;
import com.post.db.service.IndexService;
import com.post.db.service.ShelfStatisticService;
import com.post.db.service.StationInfoService;
import com.post.db.utils.StringUtils;
import com.post.db.utils.YSTime;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapServiceMain.class)
@TestPropertySource(locations = "classpath:application.yml")
public class Test {
    @Resource
    private StationDao stationDao;
    @Resource
    private PackageDao packageDao;
    @Resource
    private ShelfDao shelfDao;
    @Resource
    private PackLogDao packLogDao;
    @Resource
    private PackStatisticDao packStatisticDao;
    @Resource
    private ShelfStatisticDao shelfStatisticDao;
    @Resource
    private IndexService indexService;
    @Resource
    private ShelfStatisticService shelfStatisticService;

    @org.junit.Test
    public void test1(){
        int c = 0;
        List<Count> list = stationDao.getCount();
        stationDao.addCount(list);
        List<Count> list1 = stationDao.getCityCount();
        List<Count> list2 = stationDao.getProvinceCount();
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
        stationDao.addCount(list1);
        stationDao.addCount(list2);
    }

    @org.junit.Test
    public void test2(){
//        Pack pack = new Pack(1,"123456789","1391391399",1,YSTime.getYMDHMS(),2,1,1,null);
//        packageDao.updateOnePack(pack);
        Pack pack = packageDao.getOnePackById(1);
        System.out.println(pack.toString());
    }

    @org.junit.Test
    public void test3() {
        Shelf shelf = new Shelf();
//        int i = shelfDao.addOneShelf(shelf);
//        int i = shelfDao.updateOneShelf(shelf);
//        System.out.println(i);
//        Shelf shelf1 = shelfDao.getOneShelfById(1);
//        System.out.println(shelf1);
        List<Shelf> shelves = shelfDao.getShelfByStation(1);
        for (Shelf s: shelves) {
            s.setStock(s.getStock()-2);
        }
        int i = shelfDao.updateShelfStock(shelves);
        System.out.println(i);
//        System.out.println(shelves.toString());
//        shelves = shelfDao.getAllShelf();
//        System.out.println(shelves.toString());
    }

    @org.junit.Test
    public void test4() {
        ShelfCount shelfCounts = shelfDao.countShelfByStation(1);
        System.out.println(shelfCounts.toString());
    }

    @org.junit.Test
    public void test5() {
        List<ShelfCountByCompany> list = shelfDao.countShelfByCompany(1,2);
        System.out.println(list.toString());
    }

    @org.junit.Test
    public void test6() {
        List<PackSt> list = packLogDao.getPackInStatistic();
        int result = packStatisticDao.addInPackStatistics(list);
        System.out.println(list.toString());
        System.out.println(result);
    }
    @org.junit.Test
    public void test7() {
        List<PackSt> list = packStatisticDao.getInStatisticByStation(1,1);
        System.out.println(list.toString());
        List<PackSt> list1 = packStatisticDao.getInStatisticByStationAndCompany(1,1);
        System.out.println(list1.toString());
    }
    @org.junit.Test
    public void test8() {
        List<PackSt> list = packLogDao.getPackOutStatistic();
        System.out.println(list.toString());
        int result = packStatisticDao.addOutPackStatistics(list);
        System.out.println(result);
        List<PackSt> list1 = packStatisticDao.getOutStatisticByStation(1,1);
        System.out.println(list1.toString());
        List<PackSt> list2 = packStatisticDao.getOutStatisticByStationAndCompany(1,1);
        System.out.println(list2.toString());
    }

    @org.junit.Test
    public void test9() {
        List<ShelfSt> list = shelfStatisticDao.getInShelfStatistic(1,150);
        System.out.println(list);
        List<ShelfSt> list1 = shelfStatisticDao.getInShelfStatisticArc(1,150);
        System.out.println(list1);
        List<ShelfSt> list2 = shelfStatisticDao.getOutShelfStatistic(1,150);
        System.out.println(list2);
        List<ShelfSt> list3 = shelfStatisticDao.getOutShelfStatistic(1,150);
        System.out.println(list3);
        List<ShelfSt> list4 = shelfStatisticDao.getCurShelfInfo(1,101);
        System.out.println(list4);
    }

    @org.junit.Test
    public void test10() {
        Map<String,Object> map = indexService.getIndexData("320113");
        System.out.println(map.toString());
        Integer i = packageDao.getPackNumber("32",1);
        System.out.println(i);
    }

    @org.junit.Test
    public void test11() {
        List<Smap> list = packageDao.getPackNumberByCompanyAndArea("");
        System.out.println(list.toString());
    }

    @org.junit.Test
    public void test12() {
        String s = StringUtils.cutZero("320000");
        System.out.println(s);
    }

    //货架dao测试
    @org.junit.Test
    public void test13() {
        List<ShelfSt> list = shelfStatisticDao.getCurShelfInfo(1,101);
        for(ShelfSt s : list){
            System.out.println(s.toString());
        }
        List<Smap> smaps = shelfStatisticDao.getCurShelfInfoByShelf(1,101);
        System.out.println(smaps.toString());
    }

    @org.junit.Test
    public void test14() {
        Map<String,Object> map = shelfStatisticService.getShelfInfoByStation(1);
        System.out.println(map);
    }

    @org.junit.Test
    public void test15() {
        List<PackSt> list = packLogDao.getPackInStatistic();
        list.add(new PackSt(1,0,"1", YSTime.getYMD()));
        System.out.println(list);
        int i = packStatisticDao.addInPackStatistics(list);
        System.out.println(i);
    }

    @Resource
    private StationInfoService stationInfoService;
    @org.junit.Test
    public void test16() {
        System.out.println(stationInfoService.getStationInfoById(1));
    }

    @Resource
    private PackSendLogDao packSendLogDao;

    @org.junit.Test
    public void test17(){

        int i = packSendLogDao.addOnePack(new PackSend("231238812",4,1,YSTime.getYMDHMS()));
        System.out.println(i);

    }

    @org.junit.Test
    public void test18(){

        List<PackSend> packSends = packSendLogDao.getPacksByTerminal(1);
        System.out.println(packSends.toString());

    }

    @Resource
    private CityDao cityDao;
    @org.junit.Test
    public void test19(){
        List<City> cities = cityDao.getCityList();
        List<CityDistance> cityDistances = new ArrayList<>();
        for(int i=0; i<cities.size();i++){
            for(int j=0;j<cities.size();j++){
                if(i!=j) {
                    if (cities.get(i).getProvinceId().equals(cities.get(j).getProvinceId())) {
                        cityDistances.add(new CityDistance(cities.get(i).getCityId(), cities.get(j).getCityId(), 1));
                    } else {
                        cityDistances.add(new CityDistance(cities.get(i).getCityId(), cities.get(j).getCityId(), 2));
                    }
                    if (cityDistances.size() == 100) {
                        int k = cityDao.addCityDistance(cityDistances);
                        System.out.println(k);
                        cityDistances.clear();
                    }
                }
            }
        }
//        System.out.println(cityDistances.toString());
    }

    @Resource
    private ProvinceDao provinceDao;
    @org.junit.Test
    public void test20(){
        List<Province> provinces = provinceDao.getProvinceList();
        List<CityDistance> cityDistances = new ArrayList<>();
        for(int i=0;i<provinces.size();i++){
            for(int j=0;j<provinces.size();j++){
                if(i==j){
                    cityDistances.add(new CityDistance(provinces.get(i).getProvinceId(),provinces.get(j).getProvinceId(),1));
                }else {
                    cityDistances.add(new CityDistance(provinces.get(i).getProvinceId(),provinces.get(j).getProvinceId(),2));
                }
            }
        }
        int k = cityDao.addCityDistance(cityDistances);
        System.out.println(k);
    }

    @org.junit.Test
    public void test21(){
        List<Smap> smaps = packSendLogDao.getPackNotArrived(1,"320000");
        System.out.println(smaps);
    }


}
