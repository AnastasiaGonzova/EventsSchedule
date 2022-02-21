package com.gonzova.EventsSchedule.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "office")
public class Office extends BaseEntity{

    private String country;
    private String city;
    private String street;
    private String houseNumber;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "office")
    private Set<Room> rooms;

    public void addRoom(Room room){
        this.rooms.add(room);
        room.setOffice(this);
    }

    public void removeRoom(Room room){
        this.rooms.remove(room);
        room.setOffice(null);
    }
}
