package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room extends BaseEntity{

    private String roomName;
    private Integer placeNumber;
    private Boolean coffeeMachine;
    private Boolean smartBoard;
    private Boolean projector;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "room")
    private Set<Event> events;

    public void addEvent(Event event){
        this.events.add(event);
        event.setRoom(this);
    }

    public void removeEvent(Event event){
        this.events.remove(event);
    }
}
