package com.post.db.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页查询类")
public class QueryInfo{

    @ApiModelProperty("页数")
    private int pagenum;

    @ApiModelProperty("页大小")
    private int pagesize;

}
