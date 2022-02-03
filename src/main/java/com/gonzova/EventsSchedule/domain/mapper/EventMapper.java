package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.event.EventCreateDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Event;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface EventMapper {

    @Mapping(target = "event.id", ignore = true)
    Event fromCreateDto(EventCreateDto eventDto);

    @Mapping(target = "event.id", ignore = true)
    Event fromUpdateDto(EventUpdateDto eventDto);

    EventDto toDto (Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Event merge(@MappingTarget Event target, Event source);
}
