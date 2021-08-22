package iyp.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iyp.entity.CommonResult;
import iyp.entity.iFriend;
import iyp.mapper.FrdMapper;
import iyp.service.FriendService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JYP
 * @date 2021/7/30
 **/
@RestController
@RequestMapping("/frd")
@ApiModel("好友列表接口")
public class FrdController {

    @Resource
    private FriendService friendService;

    @GetMapping("/getFrd/{host}")
    @ApiOperation("获取好友列表")
    public CommonResult<List<iFriend>> getFrdList(@PathVariable("host") @ApiParam("用户Id") int host){

        return CommonResult.success(friendService.getFrdList(host));
    }

    @PostMapping("/addfrd/{host}/{frd}")
    @ApiOperation("添加好友")
    public CommonResult<String> addFrd(@PathVariable("host") int host,@PathVariable("frd")int frd){
        return CommonResult.success(friendService.addFrd(host, frd) ? "true" : "false");
    }
}