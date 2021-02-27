package com.post.db.mail.controller;

import com.post.db.bean.RedisCli;
import com.post.db.entity.CommonResult;
import com.post.db.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(tags = "邮箱接口")
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Resource
    private MailService mailService;


    @PostMapping("/get")
    @ApiOperation("获取验证码接口")
    public CommonResult getCode(@ApiParam("接收邮箱")@RequestParam String mail){
        String code = mailService.getEmailCode(mail);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("验证码");
        mailMessage.setText("验证码是："+code);
        mailMessage.setFrom("jyp514@qq.com");
        mailMessage.setTo(mail);
        mailSender.send(mailMessage);
        return CommonResult.success("发送成功");
    }

    @PostMapping("/test")
    @ApiOperation("验证验证码接口")
    public CommonResult testCode(@ApiParam("验证码")@RequestParam String code,@ApiParam("接收邮箱") @RequestParam String mail){
        boolean b = mailService.testCode(mail,code);
        if(b){
            return CommonResult.success(true,"验证通过了");
        }else {
            return CommonResult.failed("验证码无效或已过期");
        }
    }

}
