package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.entity.Role;
import com.gonzova.EventsSchedule.domain.mapper.EmployeeMapper;
import com.gonzova.EventsSchedule.repository.EmployeeRepository;
import com.gonzova.EventsSchedule.repository.RoleRepository;
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
    private RoleRepository roleRepository;

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

    @Override
    @Transactional
    public Employee assignRole(UUID employeeId, UUID roleId) {
        Employee employee = employeeRepository.getById(employeeId);
        Role role = roleRepository.getById(roleId);
        employee.addRole(role);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public Employee removeRole(UUID employeeId, UUID roleId) {
        Employee employee = employeeRepository.getById(employeeId);
        Role role = roleRepository.getById(roleId);
        employee.removeRole(role);
        return employeeRepository.saveAndFlush(employee);
    }

}
