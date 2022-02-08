package com.gonzova.EventsSchedule.domain.dto.office;

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
public class OfficeCreateDto {

    @NotBlank
    @Named
    String country;

    @NotBlank
    @Named
    String city;

    @NotBlank
    @NamedWithDigit
    String street;

    @NotBlank
    @NamedWithDigit
    String houseNumber;
}
