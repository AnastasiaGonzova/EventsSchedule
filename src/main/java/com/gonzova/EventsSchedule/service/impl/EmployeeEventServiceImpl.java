package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.entity.Event;
import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEvent;
import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEventKey;
import com.gonzova.EventsSchedule.repository.EmployeeEventRepository;
import com.gonzova.EventsSchedule.service.EmployeeEventService;
import com.gonzova.EventsSchedule.service.EmployeeService;
import com.gonzova.EventsSchedule.service.EventService;
import com.gonzova.EventsSchedule.service.MailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeEventServiceImpl implements EmployeeEventService {

    @NonNull
    private EmployeeEventRepository employeeEventRepository;

    @NonNull
    private EmployeeService employeeService;

    @NonNull
    private EventService eventService;

    @NonNull
    private MailService mailService;

    @Override
    public EmployeeEvent get(UUID employeeId, UUID eventId) {
        EmployeeEventKey key = new EmployeeEventKey();
        key.setEmployeeId(employeeId);
        key.setEventId(eventId);
        return employeeEventRepository.getById(key);
    }

    @Override
    public EmployeeEvent getAndInitialize(UUID employeeId, UUID eventId) {
        EmployeeEventKey key = new EmployeeEventKey();
        key.setEmployeeId(employeeId);
        key.setEventId(eventId);
        EmployeeEvent employeeEvent = employeeEventRepository.getById(key);
        Hibernate.initialize(employeeEvent);
        Hibernate.initialize(employeeEvent.getEmployee());
        Hibernate.initialize(employeeEvent.getEvent());
        Hibernate.initialize(employeeEvent.getKey());
        return employeeEvent;
    }

    @Override
    @Transactional
    public EmployeeEvent update(UUID employeeId, UUID eventId) {
        return null;
    }

    @Override
    @Transactional
    public void delete(UUID employeeId, UUID eventId) {
        EmployeeEventKey key = new EmployeeEventKey();
        key.setEmployeeId(employeeId);
        key.setEventId(eventId);
        employeeEventRepository.deleteById(key);
    }

    @Override
    @Transactional
    public EmployeeEvent assignGuest(UUID employeeId, UUID eventId) {

        final Employee employee = employeeService.get(employeeId);
        final Event event = eventService.get(eventId);

        EmployeeEventKey key = new EmployeeEventKey();
        key.setEmployeeId(employeeId);
        key.setEventId(eventId);

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEmployee(employee);
        employeeEvent.setEvent(event);
        employeeEvent.setKey(key);
        employeeEvent.setPresence(false);
        employeeEvent.setInvitationKey(UUID.randomUUID());
        employeeEvent.addEmployeeEvent();

        if (!employee.getEmail().isEmpty()) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "You are invited to the event <%s>. Please follow the link to confirm your presence: http://localhost:8080/event-schedule/v1.0/invite/%s",
                    employee.getFirstName(),
                    event.getEventName(),
                    employeeEvent.getInvitationKey()
            );

            mailService.sendEmail(employee.getEmail(), "Invitation to the event", message);
        }


        return employeeEventRepository.saveAndFlush(employeeEvent);
    }

    @Override
    @Transactional
    public EmployeeEvent removeGuest(UUID employeeId, UUID eventId) {
        EmployeeEventKey key = new EmployeeEventKey();
        key.setEmployeeId(employeeId);
        key.setEventId(eventId);

        EmployeeEvent employeeEvent = employeeEventRepository.getById(key);

        employeeEvent.removeEmployeeEvent();

        return employeeEventRepository.saveAndFlush(employeeEvent);
    }

    @Override
    @Transactional
    public EmployeeEvent confirmPresence(UUID invitationKey){
        EmployeeEvent employeeEvent = employeeEventRepository.findByInvitationKey(invitationKey);
        if(employeeEvent == null){
            return null;
        }
        employeeEvent.setPresence(true);
        employeeEvent.setInvitationKey(null);
        return employeeEventRepository.saveAndFlush(employeeEvent);
    }

}
