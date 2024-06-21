package com.fithubhome.activities.repository;

import com.fithubhome.activities.model.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciseRepository extends JpaRepository<Exercises, Integer> {
}
