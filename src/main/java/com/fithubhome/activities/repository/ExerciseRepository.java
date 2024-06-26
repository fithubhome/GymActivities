package com.fithubhome.activities.repository;

import com.fithubhome.activities.model.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercises, UUID> {
}
