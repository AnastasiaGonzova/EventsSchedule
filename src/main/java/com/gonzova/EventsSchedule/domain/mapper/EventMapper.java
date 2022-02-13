package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.event.EventCreateDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventDto;
import com.gonzova.EventsSchedule.domain.dto.event.EventUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Event;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planner", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "guest", ignore = true)
    Event fromCreateDto(EventCreateDto eventDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planner", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "guest", ignore = true)
    Event fromUpdateDto(EventUpdateDto eventDto);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "plannerId", source = "planner.id")
    EventDto toDto (Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Event merge(@MappingTarget Event target, Event source);
}
