package com.fithubhome.activities.repository;

import com.fithubhome.activities.model.GymEvent;
import com.fithubhome.activities.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantsRepository extends JpaRepository<Participant, Integer> {
    @Query("SELECT p FROM Participant p WHERE p.event = :event")
    List<Participant> getParticipantsForEvent(@Param("event") GymEvent event);
}
