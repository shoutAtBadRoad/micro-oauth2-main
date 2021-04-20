import com.post.db.MapServiceMain;
import com.post.db.dao.PackLogDao;
import com.post.db.entities.Smap;
import com.post.db.entities.SmapDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author JYP
 * @date 2021/4/20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapServiceMain.class)
@TestPropertySource(locations = "classpath:application.yml")
public class PackLogTest {

    @Resource
    private PackLogDao packLogDao;

    @Test
    public void test(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        Smap inNumberOfShelf = packLogDao.getInNumberOfShelf(1, "1001", time);
        System.out.println(inNumberOfShelf.toString());
    }

    @Test
    public void test1(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        List<Smap> inNumberOfShelf = packLogDao.getInNumberOfAllShelf(1, time);
        System.out.println(inNumberOfShelf.toString());
    }

    @Test
    public void test2(){
        List<SmapDate> inNumberOfAllShelfByPart = packLogDao.getInNumberOfAllShelfByPart(1, 10);
        System.out.println(inNumberOfAllShelfByPart.toString());
    }

    @Test
    public void test3(){
        List<Smap> avg = packLogDao.getAvgInNumberOfAllShelfByPart(1, 10);
        System.out.println(avg.toString());
    }
}
