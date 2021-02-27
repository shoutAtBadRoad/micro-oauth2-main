package com.post.db.controller;

import com.github.pagehelper.PageInfo;
import com.post.db.entities.Shelf;
import com.post.db.entity.CommonResult;
import com.post.db.entity.QueryInfo;
import com.post.db.own.entity.QueryId;
import com.post.db.service.ShelfService;
import com.post.db.service.ShelfStatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "货架信息获取接口")
@RequestMapping("/shelf")
public class ShelfController {
    @Resource
    private ShelfService shelfService;

    @GetMapping("/getShelfList")
    @ApiOperation(value = "获取货架列表")
    public CommonResult getShelfInfo(QueryId queryId){
        PageInfo<Shelf> shelf = shelfService.getShelfByStation(queryId);
        if(shelf!=null){
            return new CommonResult(200,"获取成功",shelf);
        }else {
            return new CommonResult(400,"获取失败",null);
        }
    }


}
