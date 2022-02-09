package com.gonzova.EventsSchedule.domain.mapper;

import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeCreateDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeDto;
import com.gonzova.EventsSchedule.domain.dto.employee.EmployeeUpdateDto;
import com.gonzova.EventsSchedule.domain.entity.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Employee fromCreateDto(EmployeeCreateDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Employee fromUpdateDto(EmployeeUpdateDto userDto);


    EmployeeDto toDto (Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Employee merge(@MappingTarget Employee target, Employee source);
}
