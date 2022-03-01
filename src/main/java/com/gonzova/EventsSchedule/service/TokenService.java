package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Employee;

public interface TokenService {
    String generateToken(Employee employee);

    String extractUsernameAndValidate(String token);
}
