package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("城市间运送时间")
public class CityDistance {

    @ApiModelProperty("城市代号1")
    private String city1;

    @ApiModelProperty("城市代号2")
    private String city2;

    @ApiModelProperty("需要天数")
    private int days;
}
