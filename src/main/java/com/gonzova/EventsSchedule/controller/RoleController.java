package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.RoleDto;
import com.gonzova.EventsSchedule.service.RoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="roles")
@RequiredArgsConstructor
public class RoleController {

    @NonNull
    private RoleService roleService;

    @GetMapping("/{roleId}")
    @PreAuthorize("hasAuthority('USER')")
    public RoleDto get(@PathVariable(name="roleId") UUID id){
        return Optional.of(id)
                .map(roleService::getAndInitialize)
                .map(roleService::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name="roleId") UUID id){
        roleService.delete(id);
    }
}
