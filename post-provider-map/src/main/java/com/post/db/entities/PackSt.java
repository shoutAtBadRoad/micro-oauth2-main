package com.post.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackSt {
    private int stationId;
    private int number;
    private String expressCompany;
    private String curDate;
}
