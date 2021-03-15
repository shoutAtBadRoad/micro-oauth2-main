package com.post.db.controller;

import com.github.pagehelper.PageInfo;
import com.post.db.entity.CommonResult;
import com.post.db.entity.QueryInfo;
import com.post.db.entity.User;
import com.post.db.entity.UserDTO;
import com.post.db.holder.LoginUserHolder;
import com.post.db.mail.service.MailService;
import com.post.db.own.service.MenuService;
import com.post.db.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @PostMapping("/login")
    public CommonResult login(@RequestBody User user){
        System.out.println(user.toString());
        if(user.getUsername().equals("jyp") && user.getPassword().equals("123456")){
            return new CommonResult(200,"登陆成功","312312312312");
        }else{
            return new CommonResult(400,"用户名或密码错误",null);
        }
    }

    @Resource
    private MenuService menuService;

    @GetMapping("/menu")
    public CommonResult getMenu(){
        return CommonResult.success(menuService.getMenuList(),"获取菜单列表成功");
    }

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    @ApiOperation("请求登录用户的具体信息、是否为驿站管理员，如果是则返回其管理的驿站编号、否则返回驿站号为-1")
    public CommonResult currentUser() {
        return CommonResult.success(loginUserHolder.getCurrentUser());
    }

    @Resource
    private UserService userService;
    @Resource
    private MailService mailService;

    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public CommonResult register(@ApiParam(value = "用户信息类")User user){
        boolean b = mailService.testCode(user.getMail(),user.getCode());
        if(b) {
            return userService.registerUser(user);
        }else {
            return CommonResult.failed();
        }
    }

    @GetMapping("/getUserById/{id}")
    @ApiOperation("根据用户id获取用户信息")
    public CommonResult getUserById(@ApiParam("用户ID")@PathVariable("id") int id ) {

        User user = userService.getUserById(id);
        if(user!=null){
            return CommonResult.success(user);
        }else {
            return CommonResult.failed("查找不到此用户");
        }

    }

    @GetMapping("/getUser")
    @ApiOperation("获取用户列表接口")
    public CommonResult getUserList(@ApiParam(value = "分页查询信息")QueryInfo queryInfo){
//        System.out.println(queryInfo.toString());
        PageInfo<User> users = userService.getUsers(queryInfo);
        if(users!=null){
            return CommonResult.success(users);
        }else
            return CommonResult.failed();
    }

    @PostMapping("/reviseName")
    @ApiOperation("修改用户名接口")
    public CommonResult reviseName(@ApiParam("新用户名")@RequestParam String username,
                                   @ApiParam("用户id")@RequestParam int id){
        int code = userService.reviseUsername(username,id);
        if(code == 404){
            return CommonResult.failed("此用户名已存在");
        }else {
            return CommonResult.success("修改用户名成功");
        }
    }

    @PostMapping("/reviseMobile")
    @ApiOperation("修改用户手机号接口")
    public CommonResult reviseMobile(@ApiParam("新手机号")@RequestParam String mobile,
                                   @ApiParam("用户id")@RequestParam int id){
        int code = userService.reviseMobile(mobile,id);
        if(code == 404){
            return CommonResult.failed("此手机号已存在");
        }else {
            return CommonResult.success("修改手机号成功");
        }
    }

    @PostMapping("/reviseRole")
    @ApiOperation("修改用户角色接口")
    public CommonResult reviseRole(@ApiParam("新角色")@RequestParam String role,
                                     @ApiParam("用户id")@RequestParam int id){
        int code = userService.reviseRole(role,id);
        if(code <= 0){
            return CommonResult.failed("修改角色失败");
        }else {
            return CommonResult.success("修改角色成功");
        }
    }

    @PostMapping("/revisePassword")
    @ApiOperation("修改用户密码接口")
    public CommonResult revisePassword(@ApiParam("新密码")@RequestParam String password,
                                   @ApiParam("用户id")@RequestParam int id){
        int code = userService.revisePassword(password,id);
        if(code <= 0){
            return CommonResult.failed("修改密码失败");
        }else {
            return CommonResult.success("修改密码成功");
        }
    }

}
