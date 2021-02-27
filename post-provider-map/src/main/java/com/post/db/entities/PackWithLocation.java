package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/2/26
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("增加存放位置的快递信息类")
public class PackWithLocation extends Pack{

    @ApiModelProperty("存放位置")
    private String location;

}
