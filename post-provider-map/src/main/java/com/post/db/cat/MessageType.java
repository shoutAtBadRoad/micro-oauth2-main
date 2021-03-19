package com.post.db.cat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum MessageType {

    //趋势与流量一般几分钟计算一次，然后记录数据库并推送
    LASTESTOUTLOG(1,"最新的取件记录"),
    OUTTREND(2,"出库量趋势"),
    INTREND(3,"入库量趋势"),
    PEOPLENUM(4,"实时人流量");


    private int mid;
    private String Type;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
