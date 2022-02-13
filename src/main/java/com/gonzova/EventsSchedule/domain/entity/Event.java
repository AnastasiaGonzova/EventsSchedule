package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name="event")
public class Event extends BaseEntity{

    private String eventName;
    private String eventDescribe;
    private LocalDateTime startTime;
    private LocalDateTime  endTime;

    @ManyToOne
    @JoinColumn(name = "planner_id")
    private Employee planner;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Setter(PRIVATE)
    @ManyToMany
    @JoinTable(
            name = "employee_event",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    private Set<Employee> guest;

}
