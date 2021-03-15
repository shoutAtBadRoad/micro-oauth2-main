package com.post.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("账号信息类")
@TableName(value = "p_user")
public class User {

    @ApiModelProperty("用户id")
    @TableId(value = "id")
    private int id;

    @ApiModelProperty("用户名")
    @TableField("userName")
    private String username;

    @ApiModelProperty("密码")
    @TableField("passWd")
    private String password;

    @ApiModelProperty("手机")
    @TableField("phoneNumber")
    private String mobile;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String mail;

    @ApiModelProperty("角色")
    @TableField("category")
    private String role;

    @ApiModelProperty("验证码")
    @TableField("code")
    private String code;


}
