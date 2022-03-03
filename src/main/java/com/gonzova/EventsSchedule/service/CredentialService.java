package com.gonzova.EventsSchedule.service;

import com.gonzova.EventsSchedule.domain.entity.Credential;

import java.util.UUID;

public interface CredentialService {

    Credential create(Credential credentialJson);

    Credential update(UUID id, Credential credentialJson);

    void delete(UUID id);
}
