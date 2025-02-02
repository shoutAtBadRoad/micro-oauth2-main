package com.post.db.schedule;

import com.post.db.dao.PackageDao;
import com.post.db.entities.Pack;
import com.post.db.entities.PackStored;
import com.post.db.upservice.BusinessService;
import com.post.db.utils.RandomUtil;
import com.post.db.utils.YSTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author JYP
 * @date 2021/2/27
 * 模拟快递的入库出库
 **/
@Component
@Slf4j
public class SimulationTask {

    private final Random random = new Random();

    @Resource
    private BusinessService businessService;
    @Resource
    private PackageDao packageDao;

    @Scheduled(cron="0 0/30 * * * *")
    public void runTask(){
        int num = random.nextInt(10)+2;
        for(int i=0;i<num;i++){
            int stationId = 1+Math.floorMod(i,2);
            Pack pack = new Pack();
            pack.setPackId(RandomUtil.randomId(13));
            pack.setReceiverPhone(RandomUtil.randomId(11));
            pack.setStatus(100);
            pack.setCategory(random.nextInt(3)+501+"");
            pack.setStationId(stationId);
            pack.setExpressCompany(String.valueOf(1+random.nextInt(9)));
            pack.setShelfId(stationId==1 ? 1+Math.floorMod(i,2) : 3+Math.floorMod(i,2));
            businessService.putPackInStation(pack);
        }
        log.info("该批快递入库完成");
    }

    @Scheduled(cron="5 0/5 * * * *")
    public void runTask2() throws InterruptedException {
        List<Pack> list = packageDao.getPackListByStatus(100);
        for(Pack pack : list){
            PackStored packStored = new PackStored(pack);
            packStored.setLocation((1001+random.nextInt(2)+"")+"-"+(1+random.nextInt(5)+""));
            businessService.putPackOnShelf(packStored);
            Thread.sleep(2000);
        }
        log.info("该批快递上架完成");
    }

    @Scheduled(cron="15 0/15 * * * *")
    public void runTask3() throws InterruptedException {
        int num = random.nextInt(10);
        if(num<=1) {
            List<Pack> list = packageDao.getPackListByStatus(101);
            for (Pack pack : list) {
                businessService.getPackOffShelf(pack.getPackId());
                Thread.sleep(random.nextInt(6000) + 1000);
            }
            log.info("该批快递出库完成");
        }
    }


}
