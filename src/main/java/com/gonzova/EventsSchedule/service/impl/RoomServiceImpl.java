package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Room;
import com.gonzova.EventsSchedule.domain.mapper.RoomMapper;
import com.gonzova.EventsSchedule.repository.RoomRepository;
import com.gonzova.EventsSchedule.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @NonNull
    private RoomRepository roomRepository;

    @NonNull
    private RoomMapper roomMapper;

    @Override
    public Room get(UUID id) {
        return roomRepository.getById(id);
    }

    @Override
    public Room create(Room roomJson) {
        return roomRepository.saveAndFlush(roomJson);
    }

    @Override
    public Room update(UUID id, Room roomJson) {
        return Optional.of(id)
                .map(roomRepository::getById)
                .map(current -> roomMapper.merge(current, roomJson))
                .map(roomRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        roomRepository.deleteById(id);
    }
}
