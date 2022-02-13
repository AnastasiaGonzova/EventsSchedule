package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Event;
import com.gonzova.EventsSchedule.domain.entity.Room;

import java.util.UUID;

public interface EventService {

    Event get(UUID eventId);

    Event create(UUID employeeId, Event eventJson);

    Event update(UUID id, Event eventJson);

    void delete(UUID employeeId, UUID eventId);

    Event assignRoom(UUID roomId, Event event);

    Event assignGuest(UUID employeeId, Event event);

    Event removeRoom(UUID roomId, Event event);

    Event removeGuest(UUID employeeId, Event event);
}
