package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Employee;

import java.util.UUID;

public interface EmployeeService {

    Employee get(UUID id);

    Employee getAndInitialize(UUID id);

    Employee update(UUID id, Employee employeeJson);

    void delete(UUID id);

    Employee assignRole(UUID employeeId, UUID roleId);

    Employee removeRole(UUID employeeId, UUID roleId);
}
