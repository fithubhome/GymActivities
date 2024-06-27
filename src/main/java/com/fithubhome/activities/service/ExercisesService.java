package com.fithubhome.activities.service;

import com.fithubhome.activities.model.Exercises;
import com.fithubhome.activities.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisesService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercises> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercises saveExercise(Exercises exercise) {
        return exerciseRepository.save(exercise);
    }
}
