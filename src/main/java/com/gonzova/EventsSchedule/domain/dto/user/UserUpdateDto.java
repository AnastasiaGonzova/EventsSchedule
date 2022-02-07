package com.gonzova.EventsSchedule.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class UserUpdateDto {

    @NotBlank
    String firstName;

    @NotBlank
    String secondName;

    @NotBlank
    String lastName;

    @NotBlank
    String position;

    @NotBlank
    String department;

    @Email
    String email;
}
