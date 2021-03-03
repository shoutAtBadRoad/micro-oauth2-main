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
@ApiModel("每日驿站进出量统计类")
public class PackSt {

    @ApiModelProperty("驿站ID")
    private int stationId;

    @ApiModelProperty("数量")
    private int number;

    @ApiModelProperty("快递公司")
    private String expressCompany;

    @ApiModelProperty("日期（精确到日）")
    private String curDate;
}
