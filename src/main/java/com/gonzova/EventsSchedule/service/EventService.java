package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Event;

import java.util.UUID;

public interface EventService {

    Event get(UUID Id);

    Event getAndInitialize(UUID id);

    Event create(UUID employeeId, Event eventJson);

    Event update(UUID id, Event eventJson);

    void delete(UUID employeeId, UUID eventId);

    Event assignRoom(UUID roomId, UUID eventId);

    Event removeRoom(UUID roomId, UUID eventId);
}
