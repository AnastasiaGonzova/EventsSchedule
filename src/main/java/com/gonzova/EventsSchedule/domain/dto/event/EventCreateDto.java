package com.gonzova.EventsSchedule.domain.dto.event;

import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class EventCreateDto {

    @NotBlank
    @NamedWithDigit
    String eventName;

    @NotNull
    String eventDescribe;

    @Future
    @NotNull
    LocalDateTime startTime;

    @Future
    @NotNull
    LocalDateTime endTime;
}
