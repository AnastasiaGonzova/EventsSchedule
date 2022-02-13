package com.gonzova.EventsSchedule.domain.dto.event;

import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class EventUpdateDto {

    @NamedWithDigit
    String eventName;

    String eventDescribe;

    @Future
    LocalDateTime startTime;

    @Future
    LocalDateTime endTime;
}
