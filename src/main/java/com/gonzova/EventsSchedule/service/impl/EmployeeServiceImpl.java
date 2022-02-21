package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeMapper;
import com.gonzova.EventsSchedule.repository.EmployeeRepository;
import com.gonzova.EventsSchedule.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @NonNull
    private EmployeeRepository employeeRepository;

    @NonNull
    private EmployeeMapper employeeMapper;

    @Override
    public Employee get(UUID id) {
        return employeeRepository.getById(id);
    }

    @Override
    public Employee getAndInitialize(UUID id) {
        Employee employee = employeeRepository.getById(id);
        Hibernate.initialize(employee);
        Hibernate.initialize(employee.getGuestEvent());
        Hibernate.initialize(employee.getPlannerEvent());
        return employee;
    }

    @Override
    @Transactional
    public Employee create(Employee employeeJson) {
        return employeeRepository.saveAndFlush(employeeJson);
    }

    @Override
    @Transactional
    public Employee update(UUID id, Employee employeeJson) {
        return Optional.of(id)
                .map(employeeRepository::getById)
                .map(current -> employeeMapper.merge(current, employeeJson))
                .map(employeeRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }
}
