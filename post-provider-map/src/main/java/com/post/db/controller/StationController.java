package com.post.db.controller;

import com.post.db.bean.StationCountCache;
import com.post.db.entity.CommonResult;
import com.post.db.entities.PackSend;
import com.post.db.entities.Station;
import com.post.db.service.PackSendService;
import com.post.db.service.StationInfoService;
import com.post.db.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@Api(tags = "驿站内信息接口")
@RequestMapping("/sta")
public class StationController {

    @Resource
    private StationService stationService;
    @Resource
    private StationCountCache stationCountCache;

    @Resource
    private PackSendService packSendService;
    @Resource
    private StationInfoService stationInfoService;

    @GetMapping("/predict/{station}")
    @ApiOperation(value = "获取驿站的预计到件情况",response = PackSend.class)
    public CommonResult getPackWillArrive(@ApiParam(value = "驿站Id")@PathVariable("station")int station){
        return new CommonResult(200,"查询成功",packSendService.getPackWillArrive(station));
    }

    @GetMapping("/getDetail/{id}")
    @ApiOperation(value = "获取驿站内货架详细信息")
    public CommonResult getDetails(@PathVariable("id")@ApiParam("驿站id") int id){
        Map<String,Object> map = stationInfoService.getStationInfoById(id);
        if(map!=null){
            return CommonResult.success(map);
        }else {
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "addStation")
    @PostMapping(value = "/map/add")
    public CommonResult addOneStation(Station station){
        Integer result = stationService.addOneStation(station);
        if(result > 0){
            log.info("插入成功，result:"+result);
            return new CommonResult(200,"插入成功",station);
        }else {
            log.info("插入失败，result:"+result);
            return new CommonResult(400,"插入失败",null);
        }
    }

    @ApiOperation(value = "getStationById")
    @GetMapping(value = "/map/get/{id}")
    public CommonResult getStationById(@PathVariable("id")int id){
        Station station = stationService.getStationById(id);
        if(station != null){
            log.info("查询成功,id"+id);
            return new CommonResult(200,"查询成功",station);
        }else {
            log.info("查询失败,id"+id);
            return new CommonResult(400,"查询失败",null);
        }
    }

    @ApiOperation(value = "getStationByGeo")
    @GetMapping(value = "/map/geo")
    public CommonResult getStationByGeo(String address){
        List<Station> stations = stationService.getStationByGeo(address);
        if(stations.size()!=0){
            log.info("查询成功，条数："+stations.size());
            return new CommonResult(200,"查询成功",stations);
        }else {
            log.info("查询失败,条数"+stations.size());
            return new CommonResult(400,"查询失败",null);
        }
    }

    @ApiOperation(value = "countStation")
    @GetMapping(value = "/map/count")
    public CommonResult countStation(@RequestParam(required = false,defaultValue = "000000") String areaId) throws Exception {
        log.info("address:"+areaId);
//        int count = stationService.countStation(areaId);
        int count = stationCountCache.getCount(areaId);
        if(count>=0){
            log.info("查询成功，条数："+count);
            return new CommonResult(200,"查询成功",count);
        }else {
            log.info("查询失败,条数"+count);
            return new CommonResult(400,"查询失败",null);
        }
    }




}
