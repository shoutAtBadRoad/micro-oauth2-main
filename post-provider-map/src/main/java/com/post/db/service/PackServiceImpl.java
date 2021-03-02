package com.post.db.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.post.db.dao.PackLogDao;
import com.post.db.dao.PackageDao;
import com.post.db.dao.ShelfDao;
import com.post.db.entities.Pack;
import com.post.db.entities.PackLog;
import com.post.db.entities.PackSt;
import com.post.db.entities.PackStored;
import com.post.db.own.entity.QueryId;
import com.post.db.utils.YSTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PackServiceImpl implements PackService {
    @Resource
    private PackageDao packageDao;
    @Resource
    private PackLogDao packLogDao;
    @Resource
    private ShelfDao shelfDao;

    @Override
    public int putPackageIn(String packId, int shelfId, int company) {
        int stationId = shelfDao.getStationByShelf(shelfId);
        int res = packageDao.putPackIn(packId,shelfId,stationId,company,YSTime.getYMDHMS());
        if(res==1){
            res = packLogDao.addInLog(new PackStored());
        }else {
            return -1;
        }
        if(res==1){
            res = shelfDao.putOnePackById(shelfId);
        }else {
            return -1;
        }
        return res;
    }

    @Override
    public int getPackageInNumber(int stationId,int part) {
        return packLogDao.getPackageInNumber(stationId,part);
    }

    @Override
    public List<PackSt> getPackInStatisticDaily() {
        return packLogDao.getPackInStatistic();
    }

    @Override
    public List<PackSt> getPackOutStatisticDaily() {
        return packLogDao.getPackOutStatistic();
    }

    @Override
    public List<Pack> getPackListByMobile(String mobile) {
        return packageDao.getPackListByMobile(mobile,101);
    }

    @Override
    public PageInfo<Pack> getPackListByStation(QueryId queryId) {
        PageHelper.startPage(queryId.getPagenum(),queryId.getPagesize());
        List<Pack> packs = packageDao.getPackListByStation(queryId.getId(),101);
        PageInfo<Pack> pageInfo = new PageInfo<>(packs);
        return pageInfo;
    }

    @Override
    public PageInfo<Pack> getPackListByMobile(QueryId queryId,String mobile) {
        PageHelper.startPage(queryId.getPagenum(),queryId.getPagesize());
        List<Pack> packs = packageDao.getPackListByMobileAndStation(queryId.getId(),mobile,101);
        PageInfo<Pack> pageInfo = new PageInfo<>(packs);
        return pageInfo;
    }

    @Override
    public List<Pack> getPackListByStation(int staionId) {
        return packageDao.getPackListByStation(staionId,101);
    }
}
