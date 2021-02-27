package com.post.db.controller;

import com.post.db.entity.CommonResult;
import com.post.db.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(tags = "CityController")
public class CityController {
    @Resource
    private CityService cityService;

    @ApiOperation(value = "getCityName")
    @GetMapping(value = "/map/city/{id}")
    public CommonResult getCityName(@PathVariable("id")String cityId){
        String city = cityService.getCityName(cityId);
        if(city!=null){
            log.info("查询成功，city："+city);
            return new CommonResult(200,"查询成功",city);
        }else {
            log.info("查询失败，cityId："+cityId);
            return new CommonResult(400,"查询失败",null);
        }
    }
}
