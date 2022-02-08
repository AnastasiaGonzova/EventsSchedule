package com.gonzova.EventsSchedule.domain.dto.user;

import com.gonzova.EventsSchedule.validation.email.Email;
import com.gonzova.EventsSchedule.validation.named.Named;
import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class UserCreateDto {

    @NotBlank
    @Named
    String firstName;

    @NotBlank
    @Named
    String secondName;

    @NotBlank
    @Named
    String lastName;

    @NotBlank
    @NamedWithDigit
    String position;

    @NotBlank
    @NamedWithDigit
    String department;

    @Email
    String email;
}
