package com.post.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private int id;
    private String cityId;
    private String cityName;
    private String provinceId;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}
