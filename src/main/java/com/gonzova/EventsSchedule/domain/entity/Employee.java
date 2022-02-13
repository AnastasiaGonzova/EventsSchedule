package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

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

    @Setter(PRIVATE)
    @ManyToMany
    @JoinTable(
            name = "employee_role",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    @Setter(PRIVATE)
    @ManyToMany(mappedBy = "guest")
    private Set<Event> asGuest;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "planner")
    private Set<Event> asPlanner;

    public void addEventAsPlanner(Event event){
        this.asPlanner.add(event);
        event.setPlanner(this);
    }

    public void removeEventAsPlanner(Event event){
        this.asPlanner.remove(event);
    }

    public void addEventAsGuest(Event event){
        this.asGuest.add(event);
        event.getGuest().add(this);
    }

    public void removeEventAsGuest(Event event){
        this.asGuest.remove(event);
        event.getGuest().remove(this);
    }
}
