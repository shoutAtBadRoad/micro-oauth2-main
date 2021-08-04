package iyp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author JYP
 * @date 2021/7/28
 **/
@Data
@TableName(value = "imessage")
public class UserMsg {

    private int id;
    @TableField("fromId")
    private int fromId;
    @TableField("toId")
    private int toId;
    private String content;
    private String itime;
}
