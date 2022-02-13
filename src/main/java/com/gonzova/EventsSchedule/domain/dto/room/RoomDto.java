package com.gonzova.EventsSchedule.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class RoomDto {

    UUID id;
    String roomName;
    Integer placeNumber;
    boolean coffeeMachine;
    boolean smartBoard;
    boolean projector;

    UUID officeId;

}
