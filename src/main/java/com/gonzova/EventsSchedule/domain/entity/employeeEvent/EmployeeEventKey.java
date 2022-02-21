package com.gonzova.EventsSchedule.domain.entity.employeeEvent;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class EmployeeEventKey implements Serializable {

    @Column(name="employee_id")
    private UUID employeeId;

    @Column(name="event_id")
    private UUID eventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeEventKey that = (EmployeeEventKey) o;
        return employeeId.equals(that.employeeId) &&
                eventId.equals(that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, eventId);
    }
}
