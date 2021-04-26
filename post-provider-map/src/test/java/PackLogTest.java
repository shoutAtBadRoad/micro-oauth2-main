import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.post.db.MapServiceMain;
import com.post.db.dao.PackLogDao;
import com.post.db.daomp.entity.packStatus;
import com.post.db.daomp.packStatusMapper;
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
    @Resource
    private packStatusMapper packStatusMapper;

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
        List<SmapDate> inNumberOfAllShelfByPart = packLogDao.getInNumberOfAllShelfByPart(1, 7);
        for(SmapDate smapDate : inNumberOfAllShelfByPart){
            if(smapDate.getName().equals("1001"))
            System.out.println(smapDate.toString());
        }
    }

    @Test
    public void test3(){
        List<Smap> avg = packLogDao.getAvgInNumberOfAllShelfByPart(1, 10);
        System.out.println(avg.toString());
    }

    @Test
    public void test4()
    {
        List<packStatus> list = packStatusMapper.selectList(new QueryWrapper<packStatus>().between("id",500,599));
        List<Smap> categoryOfShelf = packLogDao.getCategoryOfShelf(2, "1001");
        for(Smap smap : categoryOfShelf){
            int id = Integer.parseInt(smap.getName());
            for(packStatus p : list){
                if(p.getId() == id){
                    smap.setName(p.getName());
                }
            }
        }
        System.out.println(categoryOfShelf.toString());
    }
}
