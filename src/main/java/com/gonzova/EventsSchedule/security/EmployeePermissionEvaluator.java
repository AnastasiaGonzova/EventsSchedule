package com.gonzova.EventsSchedule.security;

import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeDto;
import com.gonzova.EventsSchedule.repository.EventRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public abstract class EmployeePermissionEvaluator implements PermissionEvaluator {

    @NonNull
    private EventRepository eventRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        EmployeeDto employeeDto = (EmployeeDto) authentication.getPrincipal();
        if (Objects.equals(targetType, "Event") && (permission.equals("UPDATE") ||permission.equals("DELETE") || permission.equals("WRITE"))) {
            return eventRepository.existsByPlannerIdAndId((UUID) targetId, employeeDto.getId());
        }
        else if (Objects.equals(targetType, "Employee") && permission.equals("UPDATE")) {
            return Objects.equals((UUID) targetId, employeeDto.getId());
        }
        return false;
    }
}
