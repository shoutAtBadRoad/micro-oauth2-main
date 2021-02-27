package com.post.db.utils;


import java.util.ArrayList;
import java.util.List;

public class StringUtils {


    public static String cutZero(String s){
        if(s.equals("100000")){
            s="000000";
        }
        if(s.length()==0) {
            return "";
        }else {
            int k = Integer.parseInt(s.substring(4, 6));
            if(k>0){
                return s;
            }else {
                int length = s.length();
                for (int i = length - 1; i >= 0; i--) {
                    if (s.charAt(i) == '0') {
                        s = s.substring(0, s.length() - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        return s;
    }

    /*
    需要拆分的str
    拆分分割字符tag
     */
    public static List<String> split(String str, char tag){
        List<String> strings = new ArrayList<>();
        List<Integer> dot = new ArrayList<>();
        dot.add(0);
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==tag){
                if(i!=0)
                dot.add(i);
            }
        }
        dot.add(str.length());
        if(str.charAt(str.length()-1)==tag){
            dot.remove(dot.size()-1);
        }
        int k = 1;
        while(dot.size()>k){
            if(k==1) {
                String subStr = str.substring(dot.get(k - 1), dot.get(k));
                strings.add(subStr);
            }else {
                String subStr = str.substring(dot.get(k - 1)+1, dot.get(k));
                strings.add(subStr);
            }
            k++;
        }
        return strings;
    }

}
