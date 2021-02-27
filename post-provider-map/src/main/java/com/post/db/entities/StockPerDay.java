package com.post.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPerDay {
    private int id;
    private int stationId;
    private int stock;
    private String curDate;
}
