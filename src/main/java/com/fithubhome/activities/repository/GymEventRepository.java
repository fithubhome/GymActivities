package com.fithubhome.activities.repository;

import com.fithubhome.activities.model.GymEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface GymEventRepository extends JpaRepository<GymEvent, Integer> {
    List<GymEvent> getEventsByDate(Date date);

    List<GymEvent> getEventsByOrganizerId(UUID organizerId);
}
