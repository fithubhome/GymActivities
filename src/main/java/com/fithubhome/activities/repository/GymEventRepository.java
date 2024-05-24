package com.fithubhome.activities.repository;

import com.fithubhome.activities.model.GymEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface GymEventRepository extends JpaRepository<GymEvent, Long> {
    List<GymEvent> getEventsByDate(Date date);

    List<GymEvent> getEventsByOrganizerId(Long organizerId);
}
