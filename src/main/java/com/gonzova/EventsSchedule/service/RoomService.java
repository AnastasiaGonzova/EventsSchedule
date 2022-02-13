package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Room;

import java.util.UUID;

public interface RoomService {

    Room get(UUID id);

    Room create(UUID officeId, Room roomJson);

    Room update(UUID roomId, Room roomJson);

    void delete(UUID officeId, UUID roomId);
}
