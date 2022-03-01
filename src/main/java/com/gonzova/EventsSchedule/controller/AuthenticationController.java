package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.LoginRequest;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeCreateDto;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeMapper;
import com.gonzova.EventsSchedule.service.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path="authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    @NonNull
    private AuthenticationService authenticationService;

    @NonNull
    private EmployeeMapper employeeMapper;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR')")
    public String signUp(@Valid @RequestBody EmployeeCreateDto employeeJson){
       return Optional.ofNullable(employeeJson)
                .map(employeeMapper::fromCreateDto)
                .map(authenticationService::signUp)
                .orElseThrow();
    }
}
