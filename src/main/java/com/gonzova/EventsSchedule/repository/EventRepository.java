package com.gonzova.EventsSchedule.repository;

import com.gonzova.EventsSchedule.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.room.id = :roomId AND e.eventDate = :eventDate")
    Collection<Event> findEventByIdAndDate(@Param("roomId") UUID roomId, @Param("eventDate") LocalDate eventDate);
}
