package com.fithubhome.activities.controller;

import com.fithubhome.activities.model.Exercises;
import com.fithubhome.activities.service.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExercisesService exercisesService;

    @GetMapping
    public ResponseEntity<List<Exercises>> getAllExercises() {
        List<Exercises> exercises = exercisesService.getAllExercises();
        return ResponseEntity.ok().body(exercises);
    }

    @PostMapping
    public ResponseEntity<Exercises> addExercise(@RequestBody Exercises exercise) {
        Exercises savedExercise = exercisesService.saveExercise(exercise);
        return ResponseEntity.ok().body(savedExercise);
    }
}
