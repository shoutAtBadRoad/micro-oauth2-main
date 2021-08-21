package iyp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/7/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "imessage")
public class UserMsg {

    private int id;
    @TableField("fromId")
    private int fromId;
    @TableField("toId")
    private int toId;
    private String content;
    private String itime;
    private String type;

    public UserMsg(ImageMsg imageMsg){
//        id = Integer.parseInt(imageMsg.getId());
        fromId = Integer.parseInt(imageMsg.getFromId());
        toId = Integer.parseInt(imageMsg.getToId());
        type = "img";
    }
}
