package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("省信息类")
public class Province {

    private int id;

    @ApiModelProperty("省Id")
    private String provinceId;

    @ApiModelProperty("省名")
    private String provinceName;

}
