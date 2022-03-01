package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.CredentialUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Credential;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

    @Mapping(target = "id", ignore = true)
    Credential fromUpdateDto(CredentialUpdateDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Credential merge(@MappingTarget Credential target, Credential source);
}
