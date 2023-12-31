package com.group.candoit.entity;

import lombok.*;

import javax.persistence.*;


@Table(name = "weather")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long weather_id;
    @Column(name="_id")
    private Long id;
    @Column(name="humidity")
    private Integer humidity;
    @Column(name="pressure")
    private Float pressure;
    @Column(name="st")
    private Float st;
    @Column(name="visibility")
    private Float visibility;
    @Column(name="wind_speed")
    private Float wind_speed;
    @Column(name="description")
    private String description;
    @Column(name="temp")
    private Float temp;
    @Column(name="wing_deg")
    private String wing_deg;
    @Column(name="tempDesc")
    private String tempDesc;

}
