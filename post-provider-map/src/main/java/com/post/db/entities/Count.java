package com.post.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Count {
    private String areaId;
    private Integer number;

    @Override
    public String toString() {
        return "Count{" +
                "areaId='" + areaId + '\'' +
                ", number=" + number +
                '}';
    }
}
