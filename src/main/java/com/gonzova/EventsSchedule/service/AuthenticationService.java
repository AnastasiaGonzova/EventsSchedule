package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.dto.LoginRequest;
import com.gonzova.EventsSchedule.domain.entity.Employee;

public interface AuthenticationService {

    String login(LoginRequest loginRequest);
    String signUp(Employee employeeJson);
}
