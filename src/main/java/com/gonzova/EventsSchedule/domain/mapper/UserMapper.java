package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.user.UserCreateDto;
import com.gonzova.EventsSchedule.domain.dto.user.UserDto;
import com.gonzova.EventsSchedule.domain.dto.user.UserUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface UserMapper {

    @Mapping(target = "user.id", ignore = true)
    User fromCreateDto(UserCreateDto userDto);

    @Mapping(target = "user.id", ignore = true)
    User fromUpdateDto(UserUpdateDto userDto);

    UserDto toDto (User user);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    User merge(@MappingTarget User target, User source);
}
