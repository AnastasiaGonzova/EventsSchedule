package com.gonzova.EventsSchedule.domain.entity;

import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEvent;
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
    @OneToMany(mappedBy = "employee")
    private Set<EmployeeEvent> guestEvent;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "planner")
    private Set<Event> plannerEvent;

    public void addEventAsPlanner(Event event){
        this.plannerEvent.add(event);
        event.setPlanner(this);
    }

    public void removeEventAsPlanner(Event event) {
        this.plannerEvent.remove(event);
        event.setPlanner(null);
    }
}
