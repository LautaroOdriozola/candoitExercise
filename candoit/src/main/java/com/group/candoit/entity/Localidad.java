package com.group.candoit.entity;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "_id")
    private String _id;
    @Column(name = "dist")
    private Float dist;
    @Column(name = "lid")
    private Float lid;
    @Column(name = "fid")
    private Float fid;
    @Column(name = "int_number")
    private Integer int_number;
    @Column(name = "name")
    private String name;
    @Column(name = "province")
    private String province;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lon")
    private String lon;
    @Column(name = "zoom")
    private String zoom;
    @Column(name = "updated")
    private Long updated;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clima_id", referencedColumnName = "id")
    private Clima weather;



}
