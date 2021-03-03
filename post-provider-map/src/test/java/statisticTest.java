import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.post.db.MapServiceMain;
import com.post.db.dao.*;
import com.post.db.daomp.PackLogMapper;
import com.post.db.entities.PackStored;
import com.post.db.entities.TimeMap;
import com.post.db.utils.YSTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapServiceMain.class)
@TestPropertySource(locations = "classpath:application.yml")
public class statisticTest {

    @Resource
    private PackageDao packageDao;

    @Test
    public void test(){
        List<TimeMap> list = packageDao.getPackInStatisticDaliy(1);
        List<TimeMap> list1 = new ArrayList<>();
        for(int i=0;i<24;i++){
            if(String.valueOf(i).length()==1) {
                list1.add(new TimeMap("0"+i,0));
            }else {
                list1.add(new TimeMap(""+i,0));
            }
        }
        for(TimeMap t:list){
            for(TimeMap t1:list1){
                if(t.getTime().equals(t1.getTime())){
                    t1.setValue(t.getValue());
                }
            }
        }
        System.out.println(list1);
        String time = YSTime.getYMDHMS(31,1,12);
        System.out.println(time);
    }

    @Resource
    private PackStatisticDao packStatisticDao;

    @Test
    public void test1(){
        List<TimeMap> list = packStatisticDao.InDaily(1);
        System.out.println(list);
        List<TimeMap> list1 = packStatisticDao.OnDaily(1);
        System.out.println(list1);
        List<TimeMap> list2 = packStatisticDao.OffDaily(1);
        System.out.println(list2);
    }

    @Resource
    private PackLogDao packLogDao;
    @Resource
    private ShelfDao shelfDao;
    @Test
    public void test2(){
        System.out.println(packLogDao.getInCountToday(1));
        System.out.println(packLogDao.getOutCountToday(1));
        System.out.println(shelfDao.countShelfByStation(1));
    }
    @Test
    public void test3(){
        System.out.println(packLogDao.getLatestOutLogs(1,10));
    }

    @Resource
    private PackLogMapper packLogMapper;
    @Test
    public void test4(){
        QueryWrapper<PackStored> wrapper = new QueryWrapper<>();
        List<PackStored> list = packLogMapper.selectList(wrapper.eq("station",1)
                                                                .between("curDate",YSTime.getYMDHMS(1,0,0),YSTime.getYMDHMS()));
        System.out.println(list.size());
    }

    @Resource
    private ShelfStatisticDao shelfStatisticDao;
    @Test
    public void test5(){
        System.out.println(shelfStatisticDao.getCurShelfInfo(1,102));
        System.out.println(shelfStatisticDao.getCurShelfInfoByShelf(1,101));
    }

}
