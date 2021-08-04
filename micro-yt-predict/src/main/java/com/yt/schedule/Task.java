package com.yt.schedule;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.yt.core.YtStatistic;
import com.yt.entity.Pack;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class Task {

    @Resource(name = "ytStatistic")
    private YtStatistic ytStatistic;

    @Scheduled(cron="0/10 * * * * *")
    public void refresh(){
        ytStatistic.refresh();
    }

    @Scheduled(cron="0/1 * * * * *")
    public void put(){
        if(!ytStatistic.getSk())
            return;
        List<Pack> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<100;i++){
            Pack pack = new Pack();
            pack.setCanvassOrgCode("1");
            pack.setDeliveryOrgCode("100");
            pack.setCanvassTime(DateUtil.offset(new Date(), DateField.MINUTE, -1-random.nextInt(1439)));
            list.add(pack);
        }
        ytStatistic.compute(list);
        System.out.println("*******compute**********");
    }

//    @Scheduled(cron="* 0/5 * * * *")
    public void ask(){
        Thread thread1 = new Thread(() -> {
           while (true){
               List<Integer> count = ytStatistic.getCount("100");
               System.out.println(Thread.currentThread() + count.toString());
           }
        });
        Thread thread2 = new Thread(() -> {
            while (true){
                List<Integer> count = ytStatistic.getCount("100");
                System.out.println(Thread.currentThread() + count.toString());
            }
        });
        thread1.start();
        thread2.start();
    }


}
