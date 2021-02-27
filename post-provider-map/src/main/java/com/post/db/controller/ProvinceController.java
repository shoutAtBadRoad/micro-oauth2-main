package com.post.db.controller;

import com.post.db.entity.CommonResult;
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
@Api(tags = "ProvinceController")
public class ProvinceController {
    @Resource
    private ProvinceService provinceService;

    @ApiOperation(value = "getProvince")
    @GetMapping("/map/province/{id}")
    public CommonResult getProvince(@PathVariable("id") String provinceId){
        String province = provinceService.getProvince(provinceId);
        if(province!=null){
            log.info("查询成功，province："+province);
            return new CommonResult(200,"查询成功",province);
        }else {
            log.info("查询失败，provinceId："+provinceId);
            return new CommonResult(400,"查询失败",null);
        }
    }
}
