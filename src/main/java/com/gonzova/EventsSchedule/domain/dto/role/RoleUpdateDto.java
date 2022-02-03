package com.gonzova.EventsSchedule.domain.dto.role;

import com.gonzova.EventsSchedule.domain.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PRIVATE)
public class RoleUpdateDto {
    RoleName name;
}
