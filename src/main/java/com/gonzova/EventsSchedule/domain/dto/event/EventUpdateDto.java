package com.gonzova.EventsSchedule.domain.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime startTime;

    @Future
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime endTime;
}
