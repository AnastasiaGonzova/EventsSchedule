package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.role.RoleCreateDto;
import com.gonzova.EventsSchedule.domain.dto.role.RoleDto;
import com.gonzova.EventsSchedule.domain.dto.role.RoleUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.RoleMapper;
import com.gonzova.EventsSchedule.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{roleId}")
    public RoleDto get(@PathVariable(name="roleId") UUID id){
        return Optional.of(id)
                .map(roleService::get)
                .map(roleMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public RoleDto post(@Valid @RequestBody RoleCreateDto roleJson){
        return Optional.ofNullable(roleJson)
                .map(roleMapper::fromCreateDto)
                .map(roleService::create)
                .map(roleMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{roleId}")
    public RoleDto patch(@PathVariable(name="roleId") UUID id, @Valid @RequestBody RoleUpdateDto roleJson){
        return Optional.ofNullable(roleJson)
                .map(roleMapper::fromUpdateDto)
                .map(toUpdate-> roleService.update(id, toUpdate))
                .map(roleMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{roleId}")
    public void delete(@PathVariable(name="roleId") UUID id){
        roleService.delete(id);
    }
}
