package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.entity.Event;
import com.gonzova.EventsSchedule.domain.entity.Room;
import com.gonzova.EventsSchedule.domain.mapper.EventMapper;
import com.gonzova.EventsSchedule.repository.EventRepository;
import com.gonzova.EventsSchedule.service.EmployeeService;
import com.gonzova.EventsSchedule.service.EventService;
import com.gonzova.EventsSchedule.service.RoomService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @NonNull
    private EventRepository eventRepository;

    @NonNull
    private EventMapper eventMapper;

    @NonNull
    private EmployeeService employeeService;

    @NonNull
    private RoomService roomService;

    @Override
    public Event getAndInitialize(UUID id) {
        Event event = eventRepository.getById(id);
        Hibernate.initialize(event);
        Hibernate.initialize(event.getGuests());
        Hibernate.initialize(event.getPlanner());
        Hibernate.initialize(event.getRoom());
        Hibernate.initialize(event.getStartTime());
        Hibernate.initialize(event.getEndTime());
        return event;
    }

    @Override
    public Event get(UUID id) {
        return eventRepository.getById(id);
    }

    @Override
    @Transactional
    public Event create(UUID employeeId, Event eventJson) {
        final Employee employee = employeeService.get(employeeId);
        employee.addEventAsPlanner(eventJson);
        return eventRepository.saveAndFlush(eventJson);
    }

    @Override
    @Transactional
    public Event update(UUID id, Event eventJson) {
        return Optional.of(id)
                .map(eventRepository::getById)
                .map(current -> eventMapper.merge(current, eventJson))
                .map(eventRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID employeeId, UUID eventId) {
        final Event event = eventRepository.findById(eventId).orElseThrow();
        employeeService.get(employeeId).removeEventAsPlanner(event);
        eventRepository.deleteById(eventId);
    }

    @Override
    @Transactional
    public Event assignRoom(UUID roomId, UUID eventId) {
        final Room room = roomService.get(roomId);
        final Event event = eventRepository.getById(eventId);
        Collection<Event> events = eventRepository.findEventByIdAndDate(roomId, event.getEventDate());

            for(Event e : events){
                if(event.getStartTime().compareTo(e.getStartTime()) == 1 && event.getEndTime().compareTo(e.getEndTime()) == -1)
                    throw new IllegalArgumentException();
                if(event.getStartTime().compareTo(e.getStartTime()) == 0 && event.getEndTime().compareTo(e.getEndTime()) == 0)
                    throw new IllegalArgumentException();
                if(event.getStartTime().compareTo(e.getStartTime()) == -1 && event.getEndTime().compareTo(e.getStartTime()) == 1)
                    throw new IllegalArgumentException();
                if(event.getStartTime().compareTo(e.getStartTime()) == 1 && event.getEndTime().compareTo(e.getEndTime()) == 1)
                    throw new IllegalArgumentException();
            }
        room.addEvent(event);
        return eventRepository.saveAndFlush(event);
    }

    @Override
    @Transactional
    public Event removeRoom(UUID roomId, UUID eventId) {
        final Room room = roomService.get(roomId);
        final Event event = eventRepository.getById(eventId);
        room.removeEvent(event);
        return eventRepository.saveAndFlush(event);
    }
}
