package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.repository.CredentialRepository;
import com.gonzova.EventsSchedule.service.CredentialService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl{

    @NonNull
    private CredentialRepository credentialRepository;


}
