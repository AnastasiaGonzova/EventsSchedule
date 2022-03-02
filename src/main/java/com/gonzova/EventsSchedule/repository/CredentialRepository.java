package com.gonzova.EventsSchedule.repository;

import com.gonzova.EventsSchedule.domain.entity.Credential;
import com.gonzova.EventsSchedule.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {

    Optional<Credential> findByLogin(String Login);

    @Query("SELECT e FROM Employee e WHERE e.credential.id = :credentialId")
    Optional<Employee> findEmployeeByCredentialId(@Param("credentialId") UUID credentialId);

    @Query("SELECT c FROM Credential c WHERE c.employee.id = :employeeId")
    Credential findCredentialByEmployeeId(@Param("employeeId") UUID employeeId);
}
