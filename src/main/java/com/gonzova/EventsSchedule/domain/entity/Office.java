package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "office")
public class Office extends BaseEntity{

    private String country;
    private String city;
    private String street;
    private String houseNumber;

}
