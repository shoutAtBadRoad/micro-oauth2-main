package iyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JYP
 * @date 2021/8/4
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonWsMsg<T> {

    private String type;

    private T data;

    private String chatId;
}
