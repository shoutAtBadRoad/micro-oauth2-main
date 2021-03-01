package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("记录分时统计信息类")
public class TimeMap {

    @ApiModelProperty("时间段")
    private String time;

    @ApiModelProperty("数值")
    private int value;
}
