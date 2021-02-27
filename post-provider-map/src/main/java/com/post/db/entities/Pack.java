package com.post.db.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("快递信息类")
public class Pack {

    @ApiModelProperty("快递Id")
    private int id;

    @ApiModelProperty("快递单号")
    private String packId;

    @ApiModelProperty("收件人手机号")
    private String receiverPhone;

    @ApiModelProperty("入库驿站号")
    private int stationId;

    @ApiModelProperty("入库时间")
    private String putIn;

    @ApiModelProperty("货架号")
    private int shelfId;

    @ApiModelProperty("当前状态")
    private int status;

    @ApiModelProperty("快递公司")
    private String expressCompany;

    @ApiModelProperty("快递实物图")
    private byte[] image;

    @ApiModelProperty("快递商品类别")
    private String category;

}
