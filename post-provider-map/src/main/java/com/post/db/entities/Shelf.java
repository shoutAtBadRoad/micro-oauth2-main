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
@ApiModel("货架信息类")
public class Shelf {

    @ApiModelProperty("货架ID")
    private int id;

    @ApiModelProperty("所属驿站号")
    private int stationId;

    @ApiModelProperty("货架预估总容量")
    private int capacity;

    @ApiModelProperty("货架现有快递量")
    private int stock;

    @ApiModelProperty("货架站内编号")
    private String no;

    @ApiModelProperty("货架拥有层数，可以表示为货架型号")
    private int story;
}
