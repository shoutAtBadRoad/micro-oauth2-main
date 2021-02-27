package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("快递进出记录")
public class PackLog {

    @ApiModelProperty("快递单号")
    private String packId;

    @ApiModelProperty("发生时间")
    private String curDate;

}
