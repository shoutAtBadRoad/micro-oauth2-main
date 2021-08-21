package iyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/8/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageMsg {

    private String id;

    private String fromId;

    private String toId;

    private String imageName;

    private String content;

    private int num;

}
