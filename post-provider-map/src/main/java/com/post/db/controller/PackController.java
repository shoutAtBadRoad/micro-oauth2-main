package com.post.db.controller;

import com.github.pagehelper.PageInfo;
import com.post.db.dao.PackageDao;
import com.post.db.dao.ShelfStatisticDao;
import com.post.db.entities.Pack;
import com.post.db.entities.Smap;
import com.post.db.entity.CommonResult;
import com.post.db.entities.PackSt;
import com.post.db.entity.ImageByte;
import com.post.db.own.entity.QueryId;
import com.post.db.service.PackService;
import com.post.db.service.PackStatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "快递信息获取接口，主要为驿站页面提供数据")
@RequestMapping("/pack")
public class PackController {
    @Resource
    private PackService packService;
    @Resource
    private PackStatisticService packStatisticService;

    @GetMapping("/getList")
    @ApiOperation(value = "获取驿站内所有尚未取走的包裹信息")
    public CommonResult getList(@ApiParam("驿站号与分页信息")QueryId queryId){
        PageInfo<Pack> pageInfo = packService.getPackListByStation(queryId);
        if(pageInfo!=null){
            return CommonResult.success(pageInfo);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "包裹信息存入")
    @PostMapping("/put")
    public CommonResult putPackIn(String packId,int shelfId,int company){
        int i = packService.putPackageIn(packId,shelfId,company);
        if(i>0){
            return new CommonResult(200,"入库成功",i);
        }else {
            return new CommonResult(400,"入库异常",i);
        }
    }


    @ApiOperation(value = "查询到part天前，驿站的入库量")
    @GetMapping("/getNumByPart")
    public CommonResult getPackInNumber(int stationId,int part){
        int number = packService.getPackageInNumber(stationId,part);
        if(number>0) {
            return new CommonResult(200, "读取成功", number);
        }else {
            return new CommonResult(400,"读取失败",number);
        }
    }


    @ApiOperation(value = "查询驿站15天内的统计情况、当天的入库出库时间分布、当天的总出库入库量、剩余件、剩余容量")
    @GetMapping("/StNc15")
    public CommonResult getInPackStatisticNoCompany15(int stationId){
        Map<String,Object> map = packStatisticService.getStatisticByStation(stationId,15);
//        log.info(list.toString());
        if(map!=null){
            return new CommonResult(200,"查询成功",map);
        }else {
            return new CommonResult(400,"查询失败",null);
        }
    }

    @ApiOperation(value = "查询驿站15日内的入库情况,按快递公司分类")
    @GetMapping("/inStWc15")
    public CommonResult getInPackStatisticWithCompany15(int stationId){
        List<PackSt> list = packStatisticService.getStatisticByStationAndCompany(stationId,15);
        log.info(list.toString());
        if(list.size()!=0){
            return new CommonResult(200,"查询成功",list);
        }else {
            return new CommonResult(400,"查询失败",null);
        }
    }

    @GetMapping("/listByMobile/{mobile}")
    @ApiOperation("使用手机号查询待取快递")
    public CommonResult getPackListByMobile(@PathVariable("mobile")@ApiParam("手机号")String mobile,QueryId queryId){
        PageInfo<Pack> packs = packService.getPackListByMobile(queryId,mobile);
        if(packs!=null){
            return CommonResult.success(packs,"获取待取列表成功");
        }else {
            return CommonResult.failed("获取待取列表失败");
        }
    }

    @GetMapping("/listByStation/{station}")
    @ApiOperation("使用驿站查询待取快递")
    public CommonResult getPackListByStation(@PathVariable("station")@ApiParam("驿站号")int station){
        List<Pack> packs = packService.getPackListByStation(station);
        if(packs!=null){
            return CommonResult.success(packs,"获取列表成功");
        }else {
            return CommonResult.failed("获取列表失败");
        }
    }

    @Resource
    private ShelfStatisticDao shelfStatisticDao;

    @GetMapping("/companyShare/{station}")
    @ApiOperation("使用驿站快递公司份额")
    public CommonResult getCompanyShare(@PathVariable("station")@ApiParam("驿站号")int station){
        //装入驿站内快递公司的快递数量分布
        List<Smap> smaps = shelfStatisticDao.getCurShelfInfoByShelf(station,101);
        if(smaps!=null){
            return CommonResult.success(smaps,"获取列表成功");
        }else {
            return CommonResult.failed("获取列表失败");
        }
    }

    @Resource
    private PackageDao packageDao;

    @GetMapping("/image/{packId}")
    @ApiOperation("获取快递的实物图")
    public CommonResult getImage(@PathVariable("packId")@ApiParam("快递单号")String packId){
        ImageByte image1 = packageDao.getImage("32435243");
        if(image1==null){
            return CommonResult.failed("读取图片失败");
        }
        Base64.Encoder encoder = Base64.getEncoder();
        String image = encoder.encodeToString(image1.getImgBytes());
        return CommonResult.success(image,"读取图片成功");
    }


}
