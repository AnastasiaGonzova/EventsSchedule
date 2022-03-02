package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.office.OfficeCreateDto;
import com.gonzova.EventsSchedule.domain.dto.office.OfficeDto;
import com.gonzova.EventsSchedule.domain.dto.office.OfficeUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Office;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface OfficeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    Office fromCreateDto(OfficeCreateDto officeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    Office fromUpdateDto(OfficeUpdateDto officeDto);

    OfficeDto toDto (Office office);

    @Mapping(target = "rooms", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Office merge(@MappingTarget Office target, Office source);
}
