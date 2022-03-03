package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.dto.RoleDto;
import com.gonzova.EventsSchedule.domain.entity.Role;
import com.gonzova.EventsSchedule.repository.RoleRepository;
import com.gonzova.EventsSchedule.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @NonNull
    private RoleRepository roleRepository;

    @Override
    public Role get(UUID id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role getAndInitialize(UUID id) {
        Role role = roleRepository.getById(id);
        Hibernate.initialize(role.getName());
        Hibernate.initialize(role.getEmployees());
        return role;
    }

    @Override
    public void delete(UUID roleId) {
        roleRepository.getById(roleId);
    }

    @Override
    public RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getName())
                .build();
    }
}
