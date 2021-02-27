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
@ApiModel("快递上架信息类")
public class PackStored extends PackLog{

    @ApiModelProperty("存放的货架编号")
    private String location;

    @ApiModelProperty("所属驿站")
    private int station;

    public PackStored(PackWithLocation pack){
        this.setPackId(pack.getPackId());
        this.setCurDate(pack.getPutIn());
        this.station = pack.getStationId();
        this.location = pack.getLocation();
    }

    public PackStored(Pack pack){
        this.setPackId(pack.getPackId());
        this.station = pack.getStationId();
    }

}
