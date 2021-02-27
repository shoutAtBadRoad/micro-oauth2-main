package com.post.db.controller;

import com.post.db.entity.CommonResult;
import com.post.db.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "首页数据接口")
@RequestMapping("/map")
public class IndexController {
    @Resource
    private IndexService indexService;

    @GetMapping("/data/{areaId}")
    @ApiOperation(value = "获取首页信息")
    public CommonResult getIndexData(@PathVariable("areaId")String areaId){
        Map<String,Object> map = indexService.getIndexData(areaId);
        if(map!=null) {
            return new CommonResult(200,"读取成功",map);
        }else {
            return new CommonResult(400,"读取失败",null);
        }
    }

    @ApiOperation(value = "获取驿站经纬度")
    @GetMapping("/location")
    public CommonResult getLocations(){
        List<List<Double>> locations = indexService.getStationLocation();
        if(locations!=null) {
            return new CommonResult(200,"读取成功",locations);
        }else {
            return new CommonResult(400,"读取失败",null);
        }
    }
}
