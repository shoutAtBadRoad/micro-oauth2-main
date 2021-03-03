package com.post.db.entities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("驿站信息类")
public class Station {

    @ApiModelProperty("驿站ID")
    private int id;

    @ApiModelProperty("驿站名称")
    private String name;

    @ApiModelProperty("经度")
    private double longtitude;

    @ApiModelProperty("纬度")
    private double latitude;

    @ApiModelProperty("容量")
    private int capacity;

    @ApiModelProperty("所在地区代号")
    private String areaId;

    @ApiModelProperty("详细地址")
    private String address;

}
