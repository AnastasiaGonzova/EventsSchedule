package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.repository.CredentialRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl{

    @NonNull
    private CredentialRepository credentialRepository;


}
