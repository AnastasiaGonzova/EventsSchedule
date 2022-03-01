package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.CredentialUpdateDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeCreateDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.CredentialMapper;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeMapper;
import com.gonzova.EventsSchedule.service.CredentialService;
import com.gonzova.EventsSchedule.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="employees")
@RequiredArgsConstructor
public class EmployeeController {

    @NonNull
    private EmployeeService employeeService;

    @NonNull
    private CredentialService credentialService;

    @NonNull
    private EmployeeMapper employeeMapper;

    @NonNull
    private CredentialMapper credentialMapper;

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('USER')")
    public EmployeeDto get(@PathVariable(name="employeeId") UUID id){
        return Optional.of(id)
                .map(employeeService::getAndInitialize)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR')")
    public EmployeeDto create(@Valid @RequestBody EmployeeCreateDto employeeJson){
        return Optional.ofNullable(employeeJson)
                .map(employeeMapper::fromCreateDto)
                .map(employeeService::create)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasPermission(#id, 'Employee', 'UPDATE')")
    public EmployeeDto update(@PathVariable(name="employeeId") UUID id, @Valid @RequestBody EmployeeUpdateDto employeeJson){
        return Optional.ofNullable(employeeJson)
                .map(employeeMapper::fromUpdateDto)
                .map(toUpdate-> employeeService.update(id, toUpdate))
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'HR')")
    public void delete(@PathVariable(name="employeeId") UUID id){
        employeeService.delete(id);
    }

    @PatchMapping("/{employeeId}/credential")
    @PreAuthorize("hasPermission(#id, 'Employee', 'UPDATE')")
    public void updateCredential(@PathVariable(name="employeeId") UUID id, @RequestBody CredentialUpdateDto credentialJson){
        Optional.ofNullable(credentialJson)
                .map(credentialMapper::fromUpdateDto)
                .map(toUpdate->credentialService.update(id, toUpdate))
                .orElseThrow();
    }
}
