package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Event;

import java.util.UUID;

public interface EventService {

    Event get(UUID id);

    Event create(Event eventJson);

    Event update(UUID id, Event eventJson);

    void delete(UUID id);
}
