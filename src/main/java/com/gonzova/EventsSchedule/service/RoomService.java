package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Room;

import java.util.UUID;

public interface RoomService {

    Room get(UUID id);

    Room create(Room roomJson);

    Room update(UUID id, Room roomJson);

    void delete(UUID id);
}
