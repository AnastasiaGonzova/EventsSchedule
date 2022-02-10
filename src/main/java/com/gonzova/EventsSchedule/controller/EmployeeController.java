package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeCreateDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeMapper;
import com.gonzova.EventsSchedule.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    private EmployeeMapper employeeMapper;

    @GetMapping("/{employeeId}")
    public EmployeeDto get(@PathVariable(name="employeeId") UUID id){
        return Optional.of(id)
                .map(employeeService::get)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public EmployeeDto post(@Valid @RequestBody EmployeeCreateDto employeeJson){
        return Optional.ofNullable(employeeJson)
                .map(employeeMapper::fromCreateDto)
                .map(employeeService::create)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDto patch(@PathVariable(name="employeeId") UUID id, @Valid @RequestBody EmployeeUpdateDto employeeJson){
        return Optional.ofNullable(employeeJson)
                .map(employeeMapper::fromUpdateDto)
                .map(toUpdate-> employeeService.update(id, toUpdate))
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable(name="employeeId") UUID id){
        employeeService.delete(id);
    }
}
