package com.post.db.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("图片字节流对象")
public class ImageByte {

    @ApiModelProperty("图片字节数组")
    private byte[] imgBytes;

}
