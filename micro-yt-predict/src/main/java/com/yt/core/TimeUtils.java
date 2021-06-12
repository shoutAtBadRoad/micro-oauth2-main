package com.yt.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static int deal(Date to,Date now){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        try {

            long f = simpleFormat.parse(simpleFormat.format(to)).getTime();
            long t = simpleFormat.parse(simpleFormat.format(now)).getTime();
            return (int) ((f-t) / (1000 * 60));
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int position(Date from,Date now,int p){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        try {
            long f = simpleFormat.parse(simpleFormat.format(from)).getTime();
            long t = simpleFormat.parse(simpleFormat.format(now)).getTime();
            return (int) (((t - f) / (1000 * 60)+p)%1440);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
