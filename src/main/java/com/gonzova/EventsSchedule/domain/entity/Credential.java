package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "credential")
public class Credential extends BaseEntity{
    private String login;
    private String pass;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
