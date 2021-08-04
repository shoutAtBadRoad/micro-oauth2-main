package iyp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author JYP
 * @date 2021/7/30
 **/
@Data
@TableName("ifriend")
public class iFriend {

    @TableId
    private int idifriend;

    @TableField("host")
    private int host;

    @TableField("friend")
    private int friend;

    @TableField("friendName")
    private String friendName;

    @TableField("chatId")
    private String chatId;
}
