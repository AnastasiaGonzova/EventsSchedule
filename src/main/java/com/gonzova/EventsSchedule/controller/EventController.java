package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.event.EventCreateDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.EventMapper;
import com.gonzova.EventsSchedule.service.EventService;
import com.gonzova.EventsSchedule.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EventController {

    @NonNull
    private EventService eventService;

    @NonNull
    private EventMapper eventMapper;

    @GetMapping("/events/{eventId}")
    public EventDto get(@PathVariable(name="eventId") UUID eventId){
        return Optional.of(eventId)
                .map(eventService::get)
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @PostMapping("/employees/{employeeId}/events")
    public EventDto post(@PathVariable(name="employeeId") UUID employeeId, @Valid @RequestBody EventCreateDto eventJson){
        return Optional.ofNullable(eventJson)
                .map(eventMapper::fromCreateDto)
                .map(toCreate->eventService.create(employeeId, toCreate))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/events/{eventId}")
    public EventDto patch(@PathVariable(name="eventId") UUID id, @Valid @RequestBody EventUpdateDto eventJson){
        return Optional.ofNullable(eventJson)
                .map(eventMapper::fromUpdateDto)
                .map(toUpdate-> eventService.update(id, toUpdate))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/employees/{employeeId}/events/{eventId}")
    public void delete(@PathVariable(name="employeeId") UUID employeeId, @PathVariable(name="eventId") UUID eventId){
        eventService.delete(employeeId, eventId);
    }

    @PostMapping("/events/{eventId}/rooms/{roomId}")
    public EventDto assignRoom(@PathVariable(name="eventId") UUID eventId, @PathVariable(name="roomId") UUID roomId){
        return Optional.of(eventId)
                .map(eventService::get)
                .map(current->eventService.assignRoom(roomId, current))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @PostMapping("/events/{eventId}/employees/{employeeId}")
    public EventDto assignGuest(@PathVariable(name="eventId") UUID eventId, @PathVariable(name="employeeId") UUID employeeId){
        return Optional.of(eventId)
                .map(eventService::get)
                .map(current->eventService.assignGuest(employeeId, current))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/events/{eventId}/rooms/{roomId}")
    public EventDto removeRoom(@PathVariable(name="eventId") UUID eventId, @PathVariable(name="roomId") UUID roomId){
        return Optional.of(eventId)
                .map(eventService::get)
                .map(current->eventService.removeRoom(roomId, current))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/events/{eventId}/employees/{employeeId}")
    public EventDto removeGuest(@PathVariable(name="eventId") UUID eventId, @PathVariable(name="employeeId") UUID employeeId){
        return Optional.of(eventId)
                .map(eventService::get)
                .map(current->eventService.removeGuest(employeeId, current))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

}
