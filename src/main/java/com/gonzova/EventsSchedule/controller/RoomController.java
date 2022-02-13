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
@RequestMapping
@RequiredArgsConstructor
public class RoomController {

    @NonNull
    private RoomService roomService;

    @NonNull
    private RoomMapper roomMapper;

    @GetMapping("/rooms/{roomId}")
    public RoomDto get(@PathVariable(name="roomId") UUID id){
        return Optional.of(id)
                .map(roomService::get)
                .map(roomMapper::toDto)
                .orElseThrow();
    }

    @PostMapping("/offices/{officeId}/rooms")
    public RoomDto post(@PathVariable(name="officeId") UUID officeId, @Valid @RequestBody RoomCreateDto roomJson){
        return Optional.ofNullable(roomJson)
                .map(roomMapper::fromCreateDto)
                .map(toCreate->roomService.create(officeId, toCreate))
                .map(roomMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/rooms/{roomId}")
    public RoomDto patch(@PathVariable(name="roomId") UUID roomId, @Valid @RequestBody RoomUpdateDto roomJson){
        return Optional.ofNullable(roomJson)
                .map(roomMapper::fromUpdateDto)
                .map(toUpdate->roomService.update(roomId, toUpdate))
                .map(roomMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/offices/{officeId}/rooms/{roomId}")
    public void delete(@PathVariable(name="officeId") UUID officeId, @PathVariable(name="roomId") UUID roomId){
        roomService.delete(officeId, roomId);
    }
}
