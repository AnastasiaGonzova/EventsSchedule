package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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
    private User planner;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    private Set<User> guest;
}
