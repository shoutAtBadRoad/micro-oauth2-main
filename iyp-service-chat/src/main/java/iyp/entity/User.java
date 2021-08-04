package iyp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author JYP
 * @date 2021/7/28
 **/
@Data
@TableName(value = "iuser")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "username")
    private String userName;

    @TableField("nickname")
    private String nickName;

    @TableField("password")
    private String passWord;

    @TableField("img")
    private byte[] image;

    @TableField("imgurl")
    private String imageUrl;
}
