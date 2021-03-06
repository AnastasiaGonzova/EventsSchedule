package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.EmployeeEventDto;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeEventMapper;
import com.gonzova.EventsSchedule.service.EmployeeEventService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="events")
@RequiredArgsConstructor
public class InviteController {

    @NonNull
    private EmployeeEventService employeeEventService;

    @NonNull
    private EmployeeEventMapper employeeEventMapper;

    @PostMapping("/{eventId}/employees/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasPermission(#eventId, 'Event', 'WRITE')")
    public EmployeeEventDto assignGuest(@PathVariable(name="eventId") UUID eventId, @PathVariable(name="employeeId") UUID employeeId){
        return Optional.of(eventId)
                .map(current->employeeEventService.assignGuest(employeeId, current))
                .map(employeeEventMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{eventId}/employees/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasPermission(#eventId, 'Event', 'DELETE')")
    public EmployeeEventDto removeGuest(@PathVariable(name="eventId") UUID eventId, @PathVariable(name="employeeId") UUID employeeId){
        return Optional.of(eventId)
                .map(current->employeeEventService.removeGuest(employeeId, current))
                .map(employeeEventMapper::toDto)
                .orElseThrow();
    }
}
