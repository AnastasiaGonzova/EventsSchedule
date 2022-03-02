package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Credential;
import com.gonzova.EventsSchedule.domain.mapper.CredentialMapper;
import com.gonzova.EventsSchedule.repository.CredentialRepository;
import com.gonzova.EventsSchedule.service.CredentialService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    @NonNull
    private CredentialRepository credentialRepository;

    @NonNull
    private CredentialMapper credentialMapper;

    @NonNull
    private PasswordEncoder passwordEncoder;

    @Override
    public Credential create(Credential credentialJson) {
        return credentialRepository.saveAndFlush(credentialJson);
    }

    @Override
    public Credential update(UUID id, Credential credentialJson) {
        if(credentialJson.getPass() != null){
            credentialJson.setPass(passwordEncoder.encode(credentialJson.getPass()));
        }

        return Optional.of(id)
                .map(credentialRepository::findCredentialByEmployeeId)
                .map(current -> credentialMapper.merge(current, credentialJson))
                .map(credentialRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        credentialRepository.deleteById(id);
    }
}
