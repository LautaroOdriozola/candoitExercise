package com.group.candoit.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;


@Table(name = "location")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Location {

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_id", referencedColumnName = "id")
    private Weather weather;
    @Column(name = "createdDate", nullable = false)
    @CreatedDate
    private Timestamp createdDate;
    @Column(name = "modifiedDate", nullable = false)
    @LastModifiedDate
    private Timestamp modifiedDate;

}
