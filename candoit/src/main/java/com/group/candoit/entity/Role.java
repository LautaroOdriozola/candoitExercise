package com.group.candoit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Table(name = "role")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "userRoles")
    private Set<UserEntity> users;
}
