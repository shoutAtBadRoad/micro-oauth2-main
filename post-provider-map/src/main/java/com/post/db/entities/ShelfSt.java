package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("统计货架现有快递的公司分布")
public class ShelfSt {

    @ApiModelProperty("货架ID")
    private int shelfId;

    @ApiModelProperty("驿站ID")
    private int stationId;

    @ApiModelProperty("数量")
    private int number;

    @ApiModelProperty("公司")
    private String company;
}
