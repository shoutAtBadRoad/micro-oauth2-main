package com.post.db.entities;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/4/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmapDate{

    private String name;
    private int value;
    private String curDate;

}
