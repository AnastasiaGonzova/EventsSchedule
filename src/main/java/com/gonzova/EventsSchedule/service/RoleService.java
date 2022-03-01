package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.dto.RoleDto;
import com.gonzova.EventsSchedule.domain.entity.Role;

import java.util.UUID;

public interface RoleService {
    Role get(UUID id);

    Role getAndInitialize(UUID id);

    void delete(UUID roleId);

    RoleDto toDto(Role role);
}
