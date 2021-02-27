package com.post.db.utils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.Random;

public class RandomUtil {

    public static String getrandom(){
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code.append(r);  //把每次随机出的数字拼在一起
        }
        return code.toString();
    }

    public static String randomId(int num){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<num;i++){
            int r = random.nextInt(10);
            builder.append(r);
        }
        return builder.toString();
    }
}
