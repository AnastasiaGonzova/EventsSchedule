package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Role;
import com.gonzova.EventsSchedule.domain.mapper.RoleMapper;
import com.gonzova.EventsSchedule.repository.RoleRepository;
import com.gonzova.EventsSchedule.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @NonNull
    private RoleRepository roleRepository;

    @NonNull
    private RoleMapper roleMapper;

    @Override
    public Role get(UUID id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role create(Role roleJson) {
        return roleRepository.saveAndFlush(roleJson);
    }

    @Override
    public Role update(UUID id, Role roleJson) {
        return Optional.of(id)
                .map(roleRepository::getById)
                .map(current -> roleMapper.merge(current, roleJson))
                .map(roleRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }
}
