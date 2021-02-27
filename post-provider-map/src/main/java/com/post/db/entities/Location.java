package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("经纬度坐标类")
public class Location {

    @ApiModelProperty("ID")
    private long id;

    @ApiModelProperty("经度")
    private double longtitude;

    @ApiModelProperty("纬度")
    private double latitude;
}
