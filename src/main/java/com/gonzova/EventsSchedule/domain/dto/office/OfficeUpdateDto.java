package com.gonzova.EventsSchedule.domain.dto.office;

import com.gonzova.EventsSchedule.validation.named.Named;
import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class OfficeUpdateDto {

    @Named
    String country;

    @Named
    String city;

    @NamedWithDigit
    String street;

    @NamedWithDigit
    String houseNumber;
}
