package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "寄出快递的必要信息类")
public class PackSend {

    @ApiModelProperty(value = "快递单号")
    private String packId;

    @ApiModelProperty(value = "出发站点号")
    private long origin;

    @ApiModelProperty(value = "目的站点号")
    private long terminal;

    @ApiModelProperty(value = "订单创建时间")
    private String createTime;
}
