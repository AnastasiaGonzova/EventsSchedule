package com.gonzova.EventsSchedule.domain.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.sql.Date;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class EventDto {

    UUID id;
    String eventName;
    String eventDescribe;
    Date startTime;
    Date endTime;
}
