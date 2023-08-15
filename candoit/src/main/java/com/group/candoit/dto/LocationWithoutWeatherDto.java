package com.group.candoit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationWithoutWeatherDto {

    private String _id;
    private Float dist;
    private Float lid;
    private Float fid;
    private Integer int_number;
    private String name;
    private String province;
    private String lat;
    private String lon;
    private String zoom;
    private Long updated;
}
