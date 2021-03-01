import com.github.pagehelper.PageInfo;
import com.post.db.MapServiceMain;
import com.post.db.dao.PackageDao;
import com.post.db.dao.UserDao;
import com.post.db.entities.Pack;
import com.post.db.entities.PackSt;
import com.post.db.entity.CommonResult;
import com.post.db.entity.ImageByte;
import com.post.db.entity.QueryInfo;
import com.post.db.entity.User;
import com.post.db.service.PackService;
import com.post.db.service.PackStatisticService;
import com.post.db.service.UserService;
import com.post.db.utils.YSTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapServiceMain.class)
@TestPropertySource(locations = "classpath:application.yml")
@Slf4j
public class UserTest {

    @Resource
    private UserDao userDao;

    @Test
    public void test1(){
//        int i = userDao.updateUser(new User(1,"jyp","123","15251857605","1"));
//        System.out.println(i);

        List<User> users = new ArrayList<>();
        User user = userDao.getOneUserById(1);
        users.add(user);
        User user1 = userDao.getOneUserByMobile("15251857605");
        users.add(user1);
        users.add(userDao.getOneUserByName("jyp"));
        users.forEach(u ->{
            System.out.println(u.toString());
        });

        List<User> list = userDao.getUsers();
        list.forEach(u->{
            System.out.println(u.toString());
        });
    }

    @Resource
    private UserService userService;

    @Test
    public void test2(){
        int i = userService.reviseMobile("15251857605",1);
        System.out.println(i);

        PageInfo<User> userPageInfo = userService.getUsers(new QueryInfo(1,2));
        System.out.println(userPageInfo.toString());
    }

    @Resource
    private PackageDao packageDao;
    @Test
    public void test3() throws Exception {
        FileInputStream is = new FileInputStream("C:\\Users\\jyp\\Desktop\\alipay.jpg");
        int size = is.available();
        byte[] data = new byte[size];
        is.read(data);
        is.close();
        int i = packageDao.addImage("123456789",data);
        System.out.println(i);
        Pack pack = packageDao.getOnePackById(1);
        System.out.println(pack.toString());
        Base64.Encoder encoder = Base64.getEncoder();
        String s = encoder.encodeToString(data);
        System.out.println(s);
    }

    @Test
    public void test4() throws Exception {
        ImageByte image = packageDao.getImage("123456789");
        System.out.println(Arrays.toString(image.getImgBytes()));
    }

    @Test
    public void test5() throws Exception {
        FileInputStream is = new FileInputStream("C:\\Users\\jyp\\Desktop\\bag1.jpg");
        int size = is.available();
        byte[] data = new byte[size];
        is.read(data);
        is.close();
        List<Pack> packs = packageDao.getPacks();
        for(Pack pack : packs){
            int i = packageDao.addImage(pack.getPackId(), data);
            System.out.println(i);
        }
    }

    @Test
    public void test6() throws Exception {
        FileInputStream is = new FileInputStream("C:\\Users\\56446\\Desktop\\pack.jpg");
        int size = is.available();
        byte[] data = new byte[size];
        is.read(data);
        is.close();
        Base64.Encoder encoder = Base64.getEncoder();
        String imgStr = encoder.encodeToString(data);
        System.out.println(imgStr);
//        String packId = "32435243";
//        CommonResult forObject = new RestTemplate().postForObject("http://localhost:9222/upload/packImg", null,CommonResult.class, imgStr, packId);
//        System.out.println(forObject);
    }

    @Resource
    private PackService packService;
    @Resource
    private PackStatisticService packStatisticService;

    @Test
    public void test7(){
        List<PackSt> list = packService.getPackInStatisticDaily();
        if(list.size()==0){
            list.add(new PackSt(1,0,"1", YSTime.getYMD()));
        }
        int i = packStatisticService.addInPackStatistics(list);
        if(i>0){
            log.info(Thread.currentThread().getId()+"in统计成功");
        }else {
            log.info(Thread.currentThread().getId()+"in统计失败");
        }
        list = packService.getPackOutStatisticDaily();
        if(list.size()==0){
            list.add(new PackSt(1,0,"1", YSTime.getYMD()));
        }
        i = packStatisticService.addOutPackStatistics(list);
        if(i>0){
            log.info(Thread.currentThread().getId()+"out统计成功");
        }else {
            log.info(Thread.currentThread().getId()+"out统计失败");
        }
        log.info(Thread.currentThread().getId()+"sleep.......");
    }

}
