package com.jyp.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * @author JYP
 * @date 2021/6/7
 **/
@Controller
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestBody MultipartFile file) throws FileNotFoundException {

//        byte[] bytes = file.getBytes();

//        File path = new File(ResourceUtils.getURL("classpath:").getPath());

        File upload = new File(ResourceUtils.getURL("classpath:").getPath(),"static/images/upload/");
        if(!upload.exists()) upload.mkdirs();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
        StringBuilder sb = new StringBuilder(dateFormat.format(new Date()));
        File file1 = new File(upload.getAbsolutePath(),sb.append(file.getOriginalFilename()).toString());

        try {
            file.transferTo(file1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "OK";
    }

    @PostMapping("/mupload")
    @ResponseBody
    public String multiFileUpload(@RequestBody List<MultipartFile> files) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder(ResourceUtils.getURL("classpath:").getPath());
        sb.append("static/images/upload/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
        for(MultipartFile file : files){
            File upload = new File(sb.toString(), dateFormat.format(new Date()) +
                    file.getOriginalFilename());

            try {
                file.transferTo(upload);
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        return "OK";
    }

}
