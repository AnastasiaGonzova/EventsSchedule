package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class Event extends BaseEntity{

    private String eventName;
    private String eventDescribe;
    private Date startTime;
    private Date endTime;

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
