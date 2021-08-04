package iyp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iyp.entity.UserMsg;
import iyp.service.UserMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JYP
 * @date 2021/7/29
 **/
@RestController
@RequestMapping(value = "/msg")
@ApiModel(value = "消息获取接口")
@CrossOrigin
@Slf4j
public class MsgController {

    @Resource
    private UserMsgService userMsgService;

    @GetMapping("/to/{to}")
    @ApiOperation(value = "消息获取方法")
    @CrossOrigin
    public List<UserMsg> getMsgto(@PathVariable("to") @ApiParam("收方Id") Integer to){
        List<UserMsg> msg = userMsgService.getMsg(to);
        return msg;
    }

    @GetMapping("/hist/{from}/{to}")
    @ApiOperation("获取消息记录")
    @CrossOrigin
    public List<UserMsg> getHistMsg(@PathVariable("from")int from,
                                    @PathVariable("to")int to){
        return userMsgService.getMsg(from, to);
    }


}
