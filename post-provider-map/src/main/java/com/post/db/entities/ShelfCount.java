package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("根据货架统计驿站的总容量与库存量")
public class ShelfCount {

    @ApiModelProperty("驿站ID")
    private int stationId;

    @ApiModelProperty("总容量")
    private int capacity;

    @ApiModelProperty("库存量")
    private int stock;
}
