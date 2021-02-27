package com.post.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelfCountByCompany {
    private int shelfId;
    private int stationId;
    private int JD;
    private int SF;
    private int ST;
    private int YD;
    private int YT;
    private int ZT;
    private int YZ;
    private int total;
}
