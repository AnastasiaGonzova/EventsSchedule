package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.room.RoomCreateDto;
import com.gonzova.EventsSchedule.domain.dto.room.RoomDto;
import com.gonzova.EventsSchedule.domain.dto.room.RoomUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.RoomMapper;
import com.gonzova.EventsSchedule.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="rooms")
@RequiredArgsConstructor
public class RoomController {

    @NonNull
    private RoomService roomService;

    @NonNull
    private RoomMapper roomMapper;

    @GetMapping("/{roomId}")
    public RoomDto get(@PathVariable(name="roomId") UUID id){
        return Optional.of(id)
                .map(roomService::get)
                .map(roomMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public RoomDto post(@Valid @RequestBody RoomCreateDto roomJson){
        return Optional.ofNullable(roomJson)
                .map(roomMapper::fromCreateDto)
                .map(roomService::create)
                .map(roomMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{roomId}")
    public RoomDto patch(@PathVariable(name="roomId") UUID id, @Valid @RequestBody RoomUpdateDto roomJson){
        return Optional.ofNullable(roomJson)
                .map(roomMapper::fromUpdateDto)
                .map(toUpdate->roomService.update(id, toUpdate))
                .map(roomMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable(name="roomId") UUID id){
        roomService.delete(id);
    }
}
