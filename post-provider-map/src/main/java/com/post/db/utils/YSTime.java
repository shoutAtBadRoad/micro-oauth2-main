package com.post.db.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YSTime {

    public static final String YMDStr = "yyyy-MM-dd";
    public static final String YMDHMSStr = "yyyy-MM-dd HH:mm:ss";

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

}
