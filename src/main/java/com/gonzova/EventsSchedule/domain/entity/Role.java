package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleName name;
}
