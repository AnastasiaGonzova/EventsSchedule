package com.gonzova.EventsSchedule.domain.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime startTime;

    @Future
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime endTime;

}
