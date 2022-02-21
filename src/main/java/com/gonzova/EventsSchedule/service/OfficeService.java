package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Office;

import java.util.UUID;

public interface OfficeService {

    Office get(UUID id);

    Office getAndInitialize(UUID id);

    Office create(Office officeJson);

    Office update(UUID id, Office officeJson);

    void delete(UUID id);
}
