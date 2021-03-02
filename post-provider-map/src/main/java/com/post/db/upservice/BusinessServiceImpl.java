package com.post.db.upservice;

import com.post.db.dao.PackLogDao;
import com.post.db.dao.PackageDao;
import com.post.db.dao.ShelfDao;
import com.post.db.entities.Pack;
import com.post.db.entities.PackLog;
import com.post.db.entities.PackStored;
import com.post.db.utils.StringUtils;
import com.post.db.utils.YSTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JYP
 * @date 2021/2/26
 **/
@Service("businessService")
@Slf4j
public class BusinessServiceImpl implements BusinessService{

    @Resource
    private PackageDao packageDao;
    @Resource
    private PackLogDao packLogDao;
    @Resource
    private ShelfDao shelfDao;

    @Override
    public int putPackInStation(Pack pack) {
        pack.setPutIn(YSTime.getYMDHMS());
        return packageDao.addOnePack(pack);
    }

    @Override
    public int putPackOnShelf(PackStored packStored) {
        //设置快递Id、入库时间、状态码
        packStored.setCurDate(YSTime.getYMDHMS());
        Pack pack = new Pack();
        pack.setPackId(packStored.getPackId());
        pack.setStatus(101);
        int i1 = packageDao.updateOnePack(pack);
        //获得拆分好的快递放置位置
        List<String> stringList = StringUtils.split(packStored.getLocation(),'-');
        //更新货架快递量+1
        int i = shelfDao.changePackByShelf(packStored.getStation(), stringList.get(0), 1);
        //向上架记录表中增加一条上架记录
        if(i1 ==1 && i==1)
        return packLogDao.addInLog(packStored);
        return 0;
    }

    @Override
    public int getPackOffShelf(String packId) {
        Pack pack = new Pack();
        //设置快递Id和状态码
        pack.setPackId(packId);
        pack.setStatus(102);
        //更新快递状态为已取件
        int i1 = packageDao.updateOnePack(pack);
        //获得快递所在货架位置
        PackStored packStored = packLogDao.getOneInLogByPackId(packId);
        //获得拆分好的快递放置位置
        List<String> stringList = StringUtils.split(packStored.getLocation(),'-');
        //将货架的快递量-1
        int i = shelfDao.changePackByShelf(packStored.getStation(), stringList.get(0), -1);
        //向取件记录表中添加取件记录
        if(i1==1 && i==1) {
            packStored.setCurDate(YSTime.getYMDHMS());
            return packLogDao.addOutLog(packStored);
        }
        return 0;
    }
}
