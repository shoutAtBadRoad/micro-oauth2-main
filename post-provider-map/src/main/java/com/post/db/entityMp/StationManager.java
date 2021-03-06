package com.post.db.entityMp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("驿站管理员对应关系类")
@TableName(value = "p_station_manager")
public class StationManager {

    @ApiModelProperty("管理员名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("管理的驿站编号")
    @TableField(value = "station")
    private int station;
}
