package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Office;
import com.gonzova.EventsSchedule.domain.entity.Room;
import com.gonzova.EventsSchedule.domain.mapper.RoomMapper;
import com.gonzova.EventsSchedule.repository.RoomRepository;
import com.gonzova.EventsSchedule.service.OfficeService;
import com.gonzova.EventsSchedule.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @NonNull
    private RoomRepository roomRepository;

    @NonNull
    private RoomMapper roomMapper;

    @NonNull
    private OfficeService officeService;

    @Override
    public Room get(UUID id) {
        return roomRepository.getById(id);
    }

    @Override
    public Room getAndInitialize(UUID id) {
        Room room = roomRepository.getById(id);
        Hibernate.initialize(room);
        Hibernate.initialize(room.getOffice());
        Hibernate.initialize(room.getEvents());
        return room;
    }

    @Override
    @Transactional
    public Room create(UUID officeId, Room roomJson) {
        final Office office = officeService.get(officeId);
        office.addRoom(roomJson);
        return roomRepository.saveAndFlush(roomJson);
    }

    @Override
    @Transactional
    public Room update(UUID id, Room roomJson) {
        return Optional.of(id)
                .map(roomRepository::getById)
                .map(current -> roomMapper.merge(current, roomJson))
                .map(roomRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID officeId, UUID roomId) {
        final Room room = roomRepository.findById(roomId).orElseThrow();
        officeService.get(officeId).removeRoom(room);
        roomRepository.deleteById(roomId);
    }


}
