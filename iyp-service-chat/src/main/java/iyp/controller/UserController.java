package iyp.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import iyp.entity.CommonResult;
import iyp.entity.User;
import iyp.mapper.UserMapper;
import iyp.service.ImageService;
import iyp.service.UserService;
import iyp.token.Token;
import iyp.token.TokenBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author JYP
 * @date 2021/7/29
 **/
@RestController
@RequestMapping("/user")
@Slf4j
@ApiModel("用户接口")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private ImageService imageService;
    @Resource
    private UserMapper userMapper;

    @Value("${server.port}")
    private int port;

    @Value("${myIp}")
    private String myIp;

    @PostMapping("/login")
    @ApiOperation("登录接口")
    public CommonResult<String> login(@ApiParam("用户对象") @RequestBody User user, HttpServletRequest request){
        String secret = userService.login(user);
        log.info(user.toString());
        if(!secret.equals("")) {
//            request.getSession().setAttribute("userId",);
            return CommonResult.success(secret,"登录成功");
        }
        return CommonResult.failed("登陆失败");
    }

    @GetMapping("/getUser/{userId}")
    @ApiOperation("获取用户信息")
    public CommonResult<User> getUser(@PathVariable("userId")int id){
        User user = userMapper.selectById(id);
        if (user==null)
            return CommonResult.failed("fail");
        user.setPassWord(MD5Encoder.encode(user.getPassWord().getBytes()));
        return CommonResult.success(user,"success");
    }

    @GetMapping("/getUser2")
    @ApiOperation("通过token获得用户信息")
    public CommonResult<User> getUser3(HttpServletRequest request){
        String s = request.getHeader("token");
        Token token = TokenBuilder.decode(s);
        int id = Integer.parseInt(token.getUser());
        User user = userMapper.selectById(id);
        user.setPassWord(MD5Encoder.encode(user.getPassWord().getBytes()));
        return CommonResult.success(user,"成功");
    }

    @GetMapping("/getUser")
    @ApiOperation("获取用户信息")
    public CommonResult<User> getUser2(HttpSession session){
        User user = userMapper.selectById((Serializable) session.getAttribute("userId"));
        if (user==null)
            return CommonResult.failed("fail");
        user.setPassWord(MD5Encoder.encode(user.getPassWord().getBytes()));
        return CommonResult.success(user,"success");
    }

    @PostMapping("/uploadAvatar")
    @ApiOperation("用户头像上传接口")
    public CommonResult<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId){
//        int userId = (int) request.getSession().getAttribute("userId");
        User user = new User();
        user.setId(userId);
        try {
            imageService.addImageByPack(file, user.getId());
            try {
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                if(!path.exists()) {
                    path = new File("");
                }

                File upload = new File(path.getAbsolutePath(),"static/avatar");
                if(!upload.exists()) {
                    upload.mkdirs();
                }

                File f = new File(path.getAbsolutePath(),"static/avatar/ava"+userId+".jpeg");
                // 保存到服务器

                file.transferTo(f);

                // 生成url地址，返回
                user.setImageUrl("avatar/ava"+userId+".jpeg");
                userMapper.updateById(user);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.failed("文件上传失败!");
        }
        return CommonResult.success("头像上传成功!");
    }

    @GetMapping("/getAva/{userId}")
    @ApiOperation("获取头像地址")
    public CommonResult<String> getAva(@PathVariable("userId")int userId) throws UnknownHostException {
        return CommonResult.success("http://" + myIp + ":" + port + "/" + userMapper.selectById(userId).getImageUrl(),"成功");
    }

    @GetMapping("/getAvatar/{userId}")
    @ApiOperation("获取头像")
    public CommonResult<String> getAvatar(@PathVariable("userId")
                                              @ApiParam("用户ID") int userId){
        String image = imageService.getImageByPack(userId);
        if(image!=null){
            log.info("用户 "+userId+" 获取头像");
            return CommonResult.success(image,"获取头像成功");
        }
        log.info("用户 "+userId+" 获取头像失败");
        return CommonResult.failed("获取头像失败");
    }


}
