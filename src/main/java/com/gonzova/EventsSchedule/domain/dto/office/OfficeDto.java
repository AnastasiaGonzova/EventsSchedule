package com.gonzova.EventsSchedule.domain.dto.office;

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
public class OfficeDto {

    UUID id;
    String country;
    String city;
    String street;
    String houseNumber;
}
