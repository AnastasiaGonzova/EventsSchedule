package com.gonzova.EventsSchedule.domain.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.sql.Date;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class EventUpdateDto {

    String eventName;
    String eventDescribe;
    Date startTime;
    Date endTime;
}
