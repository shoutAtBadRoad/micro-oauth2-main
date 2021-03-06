import cn.hutool.core.collection.CollUtil;
import com.post.db.MapServiceMain;
import com.post.db.bean.RedisCli;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapServiceMain.class)
@TestPropertySource(locations = "classpath:application.yml")
public class RedisTest {

    @Resource
    private RedisCli redisCli;

    @Test
    public void test(){
        redisCli.putList("list", CollUtil.toList(1,2,3,4,5));
        String time = (String) redisCli.get("time_"+"list");
        List<Integer> list = redisCli.getList("list");
        System.out.println("time is "+time);
        System.out.println("list is"+ list.size());
        redisCli.clearList("list");
    }
}
