package com.post.db.entities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Sql数值封装类")
public class Smap {

    @ApiModelProperty("数值含义")
    private String name;

    @ApiModelProperty("数值")
    private int value;

}
