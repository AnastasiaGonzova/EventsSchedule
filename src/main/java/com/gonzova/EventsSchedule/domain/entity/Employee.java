package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    private String firstName;
    private String secondName;
    private String lastName;
    private String position;
    private String department;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "employee_role",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;
}
