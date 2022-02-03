package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Role;

import java.util.UUID;

public interface RoleService {

    Role get(UUID id);

    Role create(Role roleJson);

    Role update(UUID id, Role roleJson);

    void delete(UUID id);
}
