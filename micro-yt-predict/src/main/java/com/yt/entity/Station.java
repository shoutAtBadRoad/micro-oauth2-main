package com.yt.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class Station {

    private String code;

    private Date time;

    private volatile Long four;
    private volatile Long twelve;
    private volatile Long tFour;

    public List<Long> cwList;

    public Station(String code) {
        this.code = code;
        this.four = this.twelve = this.tFour = 0L;
        cwList = new CopyOnWriteArrayList<>();
    }

    public void ThreeAsc(){
        four++;
        twelve++;
        tFour++;
    }

    public void TwoAsc(){
        twelve++;
        tFour++;
    }

    public void OneAsc(){
        tFour++;
    }

}