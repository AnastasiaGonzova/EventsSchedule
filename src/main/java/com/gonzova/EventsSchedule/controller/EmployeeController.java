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
@RequestMapping(path="users")
@RequiredArgsConstructor
public class EmployeeController {

    @NonNull
    private EmployeeService employeeService;

    @NonNull
    private EmployeeMapper employeeMapper;

    @GetMapping("/{userId}")
    public EmployeeDto get(@PathVariable(name="userId") UUID id){
        return Optional.of(id)
                .map(employeeService::get)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public EmployeeDto post(@Valid @RequestBody EmployeeCreateDto userJson){
        return Optional.ofNullable(userJson)
                .map(employeeMapper::fromCreateDto)
                .map(employeeService::create)
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{userId}")
    public EmployeeDto patch(@PathVariable(name="userId") UUID id, @Valid @RequestBody EmployeeUpdateDto userJson){
        return Optional.ofNullable(userJson)
                .map(employeeMapper::fromUpdateDto)
                .map(toUpdate-> employeeService.update(id, toUpdate))
                .map(employeeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(name="userId") UUID id){
        employeeService.delete(id);
    }
}
