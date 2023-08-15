package com.group.candoit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    private Long id;
    private Integer humidity;
    private Float pressure;
    private Float st;
    private Float visibility;
    private Float wind_speed;
    private String description;
    private Float temp;
    private String wing_deg;
    private String tempDesc;
}
