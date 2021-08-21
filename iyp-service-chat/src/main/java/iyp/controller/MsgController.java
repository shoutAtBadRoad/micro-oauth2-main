package iyp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iyp.entity.CommonResult;
import iyp.entity.CommonWsMsg;
import iyp.entity.Photo;
import iyp.entity.UserMsg;
import iyp.mapper.PhotoMapper;
import iyp.service.UserMsgService;
import iyp.wsService.impl.WsImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JYP
 * @date 2021/7/29
 **/
@RestController
@RequestMapping(value = "/msg")
@ApiModel(value = "消息获取接口")
@Slf4j
public class MsgController {

    @Resource
    private UserMsgService userMsgService;
    @Resource
    private PhotoMapper photoMapper;

    @GetMapping("/to/{to}")
    @ApiOperation(value = "消息获取方法")
    public CommonResult<List<UserMsg>> getMsgto(@PathVariable("to") @ApiParam("收方Id") Integer to){
        List<UserMsg> msg = userMsgService.getMsg(to);
        return CommonResult.success(msg);
    }

    @GetMapping("/hist/{from}/{to}")
    @ApiOperation("获取消息记录")
    public CommonResult<List<UserMsg>> getHistMsg(@PathVariable("from")int from,
                                    @PathVariable("to")int to){
        return CommonResult.success(userMsgService.getMsg(from, to));
    }

    @Resource
    private  WsImgService instance;

    @PostMapping("/img")
    @ApiOperation("聊天中传输图片")
    public CommonResult uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("imageName") String imageName){
//        WsImgService instance = WsImgService.getInstance();
        ConcurrentHashMap<String, String> imgs = instance.getImgs();
        try {
            byte[] data = file.getBytes();
            imgs.put(imageName, Base64.getEncoder().encodeToString(data));
            return CommonResult.success("成功");
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
    }

    @GetMapping("/getImg")
    @ApiOperation("聊天中传输图片")
    public CommonResult<Photo> downloadImage(String imageName){
//        WsImgService instance = WsImgService.getInstance();
        log.info(imageName);
        ConcurrentHashMap<String, String> imgs = instance.getImgs();
        try {
            String s = imgs.get(imageName);
            Photo photo = new Photo();
            if(s!=null)
                photo = new Photo(imageName,s);
            photo = photoMapper.selectById(imageName);
            return CommonResult.success(photo,"success");
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
    }



}
