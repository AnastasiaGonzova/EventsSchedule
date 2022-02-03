package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.event.EventCreateDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.EventMapper;
import com.gonzova.EventsSchedule.service.EventService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "events")
@RequiredArgsConstructor
public class EventController {

    @NonNull
    private EventService eventService;

    @NonNull
    private EventMapper eventMapper;

    @GetMapping("/{eventId}")
    public EventDto get(@PathVariable UUID id){
        return Optional.of(id)
                .map(eventService::get)
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public EventDto post(EventCreateDto eventJson){
        return Optional.ofNullable(eventJson)
                .map(eventMapper::fromCreateDto)
                .map(eventService::create)
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{eventId}")
    public EventDto patch(@PathVariable UUID id, EventUpdateDto eventJson){
        return Optional.ofNullable(eventJson)
                .map(eventMapper::fromUpdateDto)
                .map(toUpdate-> eventService.update(id, toUpdate))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{eventId}")
    public void delete(@PathVariable UUID id){
        eventService.delete(id);
    }
}