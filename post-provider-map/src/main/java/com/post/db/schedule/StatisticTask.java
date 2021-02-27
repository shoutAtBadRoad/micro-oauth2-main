package com.post.db.schedule;

import com.post.db.entities.PackSt;
import com.post.db.service.PackService;
import com.post.db.service.PackStatisticService;
import com.post.db.utils.YSTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/*
    定时任务
    统计驿站每日的进出件量
 */
@Component
@Slf4j
public class StatisticTask {

    @Resource
    private PackService packService;
    @Resource
    private PackStatisticService packStatisticService;

    /*
        每天的23点55分0秒执行此任务
     */
    @Scheduled(cron = "0 55 23 * * ?")
    public void execute()
    {
        List<PackSt> list = packService.getPackInStatisticDaily();
        if(list.size()==0){
            list.add(new PackSt(1,0,"1", YSTime.getYMD()));
        }
        int i = packStatisticService.addInPackStatistics(list);
        if(i>0){
            log.info(Thread.currentThread().getId()+"in统计成功");
        }else {
            log.info(Thread.currentThread().getId()+"in统计失败");
        }
        list = packService.getPackOutStatisticDaily();
        if(list.size()==0){
            list.add(new PackSt(1,0,"1", YSTime.getYMD()));
        }
        i = packStatisticService.addOutPackStatistics(list);
        if(i>0){
            log.info(Thread.currentThread().getId()+"out统计成功");
        }else {
            log.info(Thread.currentThread().getId()+"out统计失败");
        }
        log.info(Thread.currentThread().getId()+"sleep.......");
    }
}
