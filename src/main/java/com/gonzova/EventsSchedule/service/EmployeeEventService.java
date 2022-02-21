package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEvent;

import java.util.UUID;

public interface EmployeeEventService {
    EmployeeEvent get(UUID employeeId, UUID eventId);

    EmployeeEvent getAndInitialize(UUID employeeId, UUID eventId);

    EmployeeEvent update(UUID employeeId, UUID eventId);

    void delete(UUID employeeId, UUID eventId);

    EmployeeEvent assignGuest(UUID employeeId, UUID eventId);

    EmployeeEvent removeGuest(UUID employeeId, UUID eventId);

    EmployeeEvent confirmPresence(UUID key);
}
