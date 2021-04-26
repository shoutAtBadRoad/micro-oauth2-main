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
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "货架信息获取接口")
@RequestMapping("/shelf")
public class ShelfController {

    @Resource
    private ShelfService shelfService;
    @Resource
    private ShelfStatisticService shelfStatisticService;

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

    /**
     * 接收驿站Id,然后根据驿站Id查询驿站内的货架的使用率
     * 以列表的形式返回，其中每个值在0.0~1.0之间
     * @param stationId
     * @return
     */
    @GetMapping("/getUseRate/{stationId}")
    @ApiOperation(value = "获得货架使用率")
    public CommonResult getUseRate(@ApiParam(value = "驿站ID")@PathVariable("stationId") int stationId){
        List<Float> useRate = shelfService.getUseRate(stationId);
        if(null == useRate){
            return CommonResult.failed("这个驿站暂时还没有货架信息！");
        }
        return CommonResult.success(useRate,"查询成功");
    }

    @GetMapping("/getShelfDetails/{stationId}")
    @ApiOperation(value = "获得货架的上架、取件数量详情")
    public CommonResult getShelfDetails(@ApiParam(value = "驿站ID")@PathVariable("stationId")int stationId){
        Map<String, Object> details = shelfStatisticService.getShelfDetails(stationId);
        if(null != details){
            return CommonResult.success(details,"获取成功");
        }
        return CommonResult.failed("获取失败");
    }


}
