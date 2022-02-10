package com.gonzova.EventsSchedule.domain.dto.room;

import com.gonzova.EventsSchedule.validation.namedWithDigit.NamedWithDigit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class RoomUpdateDto {

    @NamedWithDigit
    String roomName;

    @Min(value = 1)
    @Max(value = 50)
    Integer placeNumber;

    Boolean coffeeMachine;

    Boolean smartBoard;

    Boolean projector;
}
