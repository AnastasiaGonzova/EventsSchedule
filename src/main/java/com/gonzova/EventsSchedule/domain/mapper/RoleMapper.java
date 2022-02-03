package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.role.RoleCreateDto;
import com.gonzova.EventsSchedule.domain.dto.role.RoleDto;
import com.gonzova.EventsSchedule.domain.dto.role.RoleUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface RoleMapper {

    @Mapping(target = "user.id", ignore = true)
    Role fromCreateDto(RoleCreateDto userDto);

    @Mapping(target = "user.id", ignore = true)
    Role fromUpdateDto(RoleUpdateDto userDto);

    RoleDto toDto (Role user);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Role merge(@MappingTarget Role target, Role source);
}
