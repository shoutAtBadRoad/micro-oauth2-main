package com.post.db.utils;

import com.post.db.entities.TimeMap;

import java.util.ArrayList;
import java.util.List;

public class SmoothUtil {

    public static List<TimeMap> timeMapDaily(List<TimeMap> list){
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
        return list1;
    }

}
