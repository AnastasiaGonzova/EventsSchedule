package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    private String roomName;
    private Integer placeNumber;
    private boolean coffeeMachine;
    private boolean smartBoard;
    private boolean projector;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;
}
