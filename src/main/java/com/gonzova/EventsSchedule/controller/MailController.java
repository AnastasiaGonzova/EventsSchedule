package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.EmployeeEventDto;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeEventMapper;
import com.gonzova.EventsSchedule.service.EmployeeEventService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MailController {

    @NonNull
    private EmployeeEventService employeeEventService;

    @NonNull
    private EmployeeEventMapper employeeEventMapper;

    @GetMapping("/activate/{activationCode}")
    public void activationEmail(){

    }

    @GetMapping("/invite/{invitationCode}")
    public EmployeeEventDto invitation(@PathVariable(name="invitationCode") UUID invitationCode){
        return Optional.of(employeeEventService.confirmPresence(invitationCode))
                .map(employeeEventMapper::toDto)
                .orElseThrow();
    }
}
