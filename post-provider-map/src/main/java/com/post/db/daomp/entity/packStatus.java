package com.post.db.daomp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/4/24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "yinshe")
public class packStatus {

    @TableField("id")
    private int id;

    @TableField("name")
    private String name;
}
