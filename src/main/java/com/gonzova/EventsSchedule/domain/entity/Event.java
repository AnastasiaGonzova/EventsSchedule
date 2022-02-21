package com.gonzova.EventsSchedule.domain.entity;

import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEvent;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name="event")
public class Event extends BaseEntity{

    private String eventName;
    private String eventDescribe;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "planner_id")
    private Employee planner;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "event")
    private Set<EmployeeEvent> guests;

}
