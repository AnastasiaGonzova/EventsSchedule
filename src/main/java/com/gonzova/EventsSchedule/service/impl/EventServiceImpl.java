package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Event;
import com.gonzova.EventsSchedule.domain.mapper.EventMapper;
import com.gonzova.EventsSchedule.repository.EventRepository;
import com.gonzova.EventsSchedule.service.EventService;
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
public class EventServiceImpl implements EventService {

    @NonNull
    private EventRepository eventRepository;

    @NonNull
    private EventMapper eventMapper;

    @Override
    public Event get(UUID id) {
        return eventRepository.getById(id);
    }

    @Override
    public Event create(Event eventJson) {
        return eventRepository.saveAndFlush(eventJson);
    }

    @Override
    public Event update(UUID id, Event eventJson) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> eventMapper.merge(current, eventJson))
                .map(this::create)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        eventRepository.deleteById(id);
    }
}
