package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.room.RoomCreateDto;
import com.gonzova.EventsSchedule.domain.dto.room.RoomDto;
import com.gonzova.EventsSchedule.domain.dto.room.RoomUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Room;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface RoomMapper {

    @Mapping(target = "room.id", ignore = true)
    Room fromCreateDto(RoomCreateDto roomDto);

    @Mapping(target = "room.id", ignore = true)
    Room fromUpdateDto(RoomUpdateDto roomDto);

    RoomDto toDto (Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Room merge(@MappingTarget Room target, Room source);
}