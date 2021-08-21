package iyp.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WsMsgType {

    MSG(100,"text"),
    IMAGE(101,"img"),
    GIF(102,"gif"),
    VIDEO(103,"video"),
    NOTE(200,"note"),
    TIP(300,"tip");


    private int code;

    private String type;

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
