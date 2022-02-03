package com.gonzova.EventsSchedule.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class RoomCreateDto {

    String roomName;
    Integer placeNumber;
    boolean coffeeMachine;
    boolean smartBoard;
    boolean projector;
}
