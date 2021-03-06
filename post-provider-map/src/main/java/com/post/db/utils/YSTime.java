package com.post.db.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YSTime {

    public static final String YMDStr = "yyyy-MM-dd";
    public static final String YMDHMSStr = "yyyy-MM-dd HH:mm:ss";
    public static final String HMS="HH:mm:ss";

    public YSTime(){

    }

    public static String getYMD(){
        SimpleDateFormat sdf = new SimpleDateFormat(YMDStr);
        return sdf.format(new Date());
    }

    public static String getYMDHMS(){
        SimpleDateFormat sdf = new SimpleDateFormat(YMDHMSStr);
        return sdf.format(new Date());
    }

    /*
        自定义时分秒，不满足规则返回当前时间
     */
    public static String getYMDHMS(int hour,int minute,int second){
        SimpleDateFormat sdf = new SimpleDateFormat(YMDStr);
        if(hour>=24 || hour <0 || minute<0 || minute>=60 || second<0 || second>=60){
            sdf = new SimpleDateFormat(YMDHMSStr);
            return sdf.format(new Date());
        }
        String former = sdf.format(new Date());
        String h = String.valueOf(hour);
        String i = String.valueOf(minute);
        String s = String.valueOf(second);
        if(h.length()==1){
            h="0"+h;
        }
        if(i.length()==1){
            i="0"+i;
        }
        if(s.length()==1){
            s="0"+s;
        }
        return former + " "+h+":"+i+":"+s;
    }

    public static boolean compareNow(String time){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YMDHMSStr);
            Date date = sdf.parse(time);
            DateTime date1 = new DateTime();
            date1.setField(DateField.MINUTE, 0);
            date1.setField(DateField.SECOND, 0);
            return date.before(date1);
        } catch (Exception e){
            System.out.println(e);
        }
        return true;
    }

}
