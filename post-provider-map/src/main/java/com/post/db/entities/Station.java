package com.post.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private int id;
    private String name;
    private double longtitude;
    private double latitude;
    private int capacity;
    private String areaId;
    private String address;

}
