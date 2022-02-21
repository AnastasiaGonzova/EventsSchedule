package com.gonzova.EventsSchedule.domain.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate eventDate;

    @NotNull
    @JsonFormat(pattern="HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    LocalTime startTime;

    @NotNull
    @JsonFormat(pattern="HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    LocalTime endTime;
}
