package com.gonzova.EventsSchedule.repository;

import com.gonzova.EventsSchedule.domain.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {

    Optional<Credential> findByLogin(String Login);
}
