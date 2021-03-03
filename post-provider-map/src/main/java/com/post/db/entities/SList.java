package com.post.db.entities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("图表数据列表格式封装类")
public class SList {

    @ApiModelProperty("ID")
    private int id;

    @ApiModelProperty("携带的数据列表")
    private int[] data;
}
