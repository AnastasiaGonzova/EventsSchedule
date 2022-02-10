package com.gonzova.EventsSchedule.domain.dto.employee;

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
public class EmployeeUpdateDto {

    @Named
    String firstName;

    @Named
    String secondName;

    @Named
    String lastName;

    @NamedWithDigit
    String position;

    @NamedWithDigit
    String department;

    @Email
    String email;
}
