package com.post.db.controller;

import com.post.db.entities.Pack;
import com.post.db.entities.PackStored;
import com.post.db.entity.CommonResult;
import com.post.db.upservice.BusinessService;
import com.post.db.upservice.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author JYP
 * @date 2021/2/26
 **/
@RestController
@RequestMapping("/upload")
@Slf4j
@Api(tags = "信息上传接口")
public class UploadController {

    @Resource
    private ImageService imageService;

    @PostMapping("/packImg")
    @ApiOperation("上传快递图片")
    public CommonResult uploadPackImg(@ApiParam("图片文件")@RequestParam(required = false) MultipartFile imgFile,
                                      @ApiParam("图片字符流")@RequestParam(required = false) String imgStr,
                                      @ApiParam("快递单号")@RequestParam String packId) throws Exception {
        if(imgFile==null&&imgStr==null){
            log.info("没有图片信息");
            return CommonResult.failed("没有收到文件或图片字符流");
        }
        if(imgFile!=null){
            int res = imageService.addImageByPack(imgFile,packId);
            switch (res){
                case 0 : return CommonResult.failed("图片文件保存失败");
                case 1 : return CommonResult.success("图片文件保存成功");
                default: return CommonResult.failed("异常");
            }
        }else {
            int res = imageService.addImageByPack(imgStr,packId);
            switch (res){
                case 0 : return CommonResult.failed("图片流保存失败");
                case 1 : return CommonResult.success("图片流保存成功");
                default: return CommonResult.failed("异常");
            }
        }
    }

    @Resource
    private BusinessService businessService;

    @PostMapping("/putInPack")
    @ApiOperation("快递扫码入库接口")
    public CommonResult storePack(@ApiParam("快递信息")@RequestBody Pack pack){
        int i = businessService.putPackInStation(pack);
        switch (i) {
            case 0: return CommonResult.failed();
            case 1: return CommonResult.success(pack.getPackId()+"入库成功");
            default: return CommonResult.failed("入库异常");
        }
    }

    @PostMapping("/storePack")
    @ApiOperation("快递扫码上架接口")
    public CommonResult storePack(@ApiParam("快递信息")@RequestBody PackStored pack){
        int i = businessService.putPackOnShelf(pack);
        switch (i) {
            case 0: return CommonResult.failed();
            case 1: return CommonResult.success(pack.getPackId()+"上架成功");
            default: return CommonResult.failed("上架异常");
        }
    }

    @GetMapping("/getPack/{packId}")
    @ApiOperation("快递扫码出库接口")
    public CommonResult getPack(@ApiParam("快递单号")@PathVariable("packId") String packId){
        int i = businessService.getPackOffShelf(packId);
        switch (i) {
            case 0: return CommonResult.failed();
            case 1: return CommonResult.success(packId+"出库成功");
            default: return CommonResult.failed("出库异常");
        }
    }



}
