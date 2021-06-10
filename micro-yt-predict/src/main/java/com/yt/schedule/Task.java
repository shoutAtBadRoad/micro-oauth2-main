package com.yt.schedule;

import com.yt.core.YtStatistic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Task {

    @Resource(name = "ytStatistic")
    private YtStatistic ytStatistic;

    @Scheduled(cron="0 0/1 * * * *")
    public void refresh(){
        ytStatistic.refresh();
    }

}
