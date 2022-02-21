package com.gonzova.EventsSchedule.repository;

import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEvent;
import com.gonzova.EventsSchedule.domain.entity.employeeEvent.EmployeeEventKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, EmployeeEventKey> {

    EmployeeEvent findByInvitationKey(UUID key);
}
