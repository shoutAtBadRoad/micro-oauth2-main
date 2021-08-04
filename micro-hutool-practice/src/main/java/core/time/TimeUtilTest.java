package core.time;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author JYP
 * @date 2021/6/13
 **/

public class TimeUtilTest {

    @Test
    public void test1(){
        //当前时间
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();

        System.out.println(date);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(now);
        System.out.println(today);
    }

    @Test
    public void test2(){

        TimeInterval timer = DateUtil.timer();

        String str = "2019-12-01 12:00:01";
        DateTime time = DateUtil.parse(str);
        System.out.println(time);

        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);

//结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");

//常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);

//结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);

//结果：00:00:00
        String formatTime = DateUtil.formatTime(date);

        /**
         * 从0开始
         */
        System.out.println(DateUtil.month(date));

        System.out.println(DateUtil.beginOfDay(time));

        System.out.println(DateUtil.offset(time, DateField.DAY_OF_WEEK, -1));

        System.out.println(timer.interval());
    }



}
