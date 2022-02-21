package com.gonzova.EventsSchedule.domain.entity.employeeEvent;

import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.entity.Event;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="employee_event")
public class EmployeeEvent {

    @EmbeddedId
    private EmployeeEventKey key;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    private UUID invitationKey;

    private Boolean presence;

    public void addEmployeeEvent(){
        employee.getGuestEvent().add(this);
        event.getGuests().add(this);
    }

    public void removeEmployeeEvent(){
        employee.getGuestEvent().remove(this);
        event.getGuests().remove(this);
    }
}
