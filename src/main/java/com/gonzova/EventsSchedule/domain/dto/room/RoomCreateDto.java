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
public class RoomCreateDto {

    @NotBlank
    @NamedWithDigit
    String roomName;

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    Integer placeNumber;

    @NotNull
    Boolean coffeeMachine;

    @NotNull
    Boolean smartBoard;

    @NotNull
    Boolean projector;

}
