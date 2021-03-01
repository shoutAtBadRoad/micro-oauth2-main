import com.post.db.MapServiceMain;
import com.post.db.dao.PackageDao;
import com.post.db.entities.TimeMap;
import com.post.db.utils.YSTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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

}
