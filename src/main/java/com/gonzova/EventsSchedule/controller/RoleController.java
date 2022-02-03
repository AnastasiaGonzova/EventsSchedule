package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.role.RoleCreateDto;
import com.gonzova.EventsSchedule.domain.dto.role.RoleDto;
import com.gonzova.EventsSchedule.domain.dto.role.RoleUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.RoleMapper;
import com.gonzova.EventsSchedule.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "roles")
@RequiredArgsConstructor
public class RoleController {

    @NonNull
    private RoleService roleService;

    @NonNull
    private RoleMapper roleMapper;

    @GetMapping("/{userId}")
    public RoleDto get(@PathVariable UUID id){
        return Optional.of(id)
                .map(roleService::get)
                .map(roleMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public RoleDto post(RoleCreateDto roleJson){
        return Optional.ofNullable(roleJson)
                .map(roleMapper::fromCreateDto)
                .map(roleService::create)
                .map(roleMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{roleId}")
    public RoleDto patch(@PathVariable UUID id, RoleUpdateDto roleJson){
        return Optional.ofNullable(roleJson)
                .map(roleMapper::fromUpdateDto)
                .map(toUpdate-> roleService.update(id, toUpdate))
                .map(roleMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{roleId}")
    public void delete(@PathVariable UUID id){
        roleService.delete(id);
    }
}
