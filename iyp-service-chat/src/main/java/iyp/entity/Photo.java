package iyp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/8/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("iPhoto")
public class Photo {

    @TableId
    private String id;

    @TableField("img")
    private String img;
}
