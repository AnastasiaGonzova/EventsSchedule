package com.gonzova.EventsSchedule.repository;

import com.gonzova.EventsSchedule.domain.emuns.RoleName;
import com.gonzova.EventsSchedule.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(RoleName name);
}
