package com.gonzova.EventsSchedule.domain.dto.office;

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
public class OfficeUpdateDto {

    @NotBlank
    String country;

    @NotBlank
    String city;

    @NotBlank
    String street;

    @NotBlank
    String houseNumber;
}
