package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.CredentialUpdateDto;
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
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public EmployeeDto get(@PathVariable(name="employeeId") UUID id){
        return Optional.of(id)
                .map(employeeService::getAndInitialize)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasPermission(#id, 'Employee', 'UPDATE')")
    public EmployeeDto update(@PathVariable(name="employeeId") UUID id, @Valid @RequestBody EmployeeUpdateDto employeeJson){
        return Optional.ofNullable(employeeJson)
                .map(employeeMapper::fromUpdateDto)
                .map(toUpdate-> employeeService.update(id, toUpdate))
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_HR')")
    public void delete(@PathVariable(name="employeeId") UUID id){
        employeeService.delete(id);
    }


    @PatchMapping("/{employeeId}/profile")
    @PreAuthorize("hasPermission(#id, 'Employee', 'UPDATE')")
    public void updateCredential(@PathVariable(name="employeeId") UUID id, @RequestBody CredentialUpdateDto credentialJson){
        Optional.ofNullable(credentialJson)
                .map(credentialMapper::fromUpdateDto)
                .map(toUpdate->credentialService.update(id, toUpdate))
                .orElseThrow();
    }

    @PostMapping("/{employeeId}/roles/{roleId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_HR')")
    public EmployeeDto assignRole(@PathVariable(name="employeeId") UUID employeeId, @PathVariable(name="roleId") UUID roleId){
        return Optional.of(employeeId)
                .map(current -> employeeService.assignRole(current, roleId))
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{employeeId}/roles/{roleId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_HR')")
    public EmployeeDto removeRole(@PathVariable(name="employeeId") UUID employeeId, @PathVariable(name="roleId") UUID roleId){
        return Optional.of(employeeId)
                .map(current -> employeeService.removeRole(current, roleId))
                .map(employeeMapper::toDto)
                .orElseThrow();
    }
}
