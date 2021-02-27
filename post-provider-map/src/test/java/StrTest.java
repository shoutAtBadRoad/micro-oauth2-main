import com.post.db.MapServiceMain;
import com.post.db.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JYP
 * @date 2021/2/27
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapServiceMain.class)
@TestPropertySource(locations = "classpath:application.yml")
public class StrTest {

    @Test
    public void test1(){
        System.out.println(StringUtils.split("1001-1-101-", '-'));
    }
}
