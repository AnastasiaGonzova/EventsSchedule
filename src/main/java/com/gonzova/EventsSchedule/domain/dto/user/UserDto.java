package com.gonzova.EventsSchedule.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class UserDto {

    UUID id;
    String firstName;
    String secondName;
    String lastName;
    String position;
    String department;
    String email;
}
