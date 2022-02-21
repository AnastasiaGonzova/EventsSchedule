package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.EmployeeEventDto;
import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeEventMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "eventId", source = "event.id")
    EmployeeEventDto toDto (EmployeeEvent employeeEvent);
}
