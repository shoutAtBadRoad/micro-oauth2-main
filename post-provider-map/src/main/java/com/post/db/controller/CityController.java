package com.post.db.controller;

import com.post.db.entity.CommonResult;
import com.post.db.service.CityService;
import com.post.db.service.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(tags = "获取城市信息")
public class CityController {
    @Resource
    private CityService cityService;
    @Resource
    private ProvinceService provinceService;

    @ApiOperation(value = "获取县区信息")
    @GetMapping(value = "/map/city/{id}")
    public CommonResult<String> getCityName(@PathVariable("id")String cityId){
        String city = cityService.getCityName(cityId);
        if(city!=null){
            log.info("查询成功，city："+city);
            return new CommonResult<String>(200,"查询成功",city);
        }else {
            log.info("查询失败，cityId："+cityId);
            return new CommonResult<String>(400,"查询失败",null);
        }
    }

    @ApiOperation(value = "获取省信息")
    @GetMapping("/map/province/{id}")
    public CommonResult<String> getProvince(@PathVariable("id") String provinceId){
        String province = provinceService.getProvince(provinceId);
        if(province!=null){
            log.info("查询成功，province："+province);
            return new CommonResult<String>(200,"查询成功",province);
        }else {
            log.info("查询失败，provinceId："+provinceId);
            return new CommonResult<String>(400,"查询失败",null);
        }
    }
}
