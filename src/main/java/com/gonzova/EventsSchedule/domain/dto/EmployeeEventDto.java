package com.gonzova.EventsSchedule.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class EmployeeEventDto {

    UUID employeeId;
    UUID eventId;
    UUID invitationKey;
    Boolean presence;
}
