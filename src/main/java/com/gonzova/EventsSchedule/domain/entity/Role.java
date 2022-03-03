package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    private String name;

    @Setter(PRIVATE)
    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees;
}
