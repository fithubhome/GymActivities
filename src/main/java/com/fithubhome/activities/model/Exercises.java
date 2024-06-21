package com.fithubhome.activities.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercises")
@AllArgsConstructor
@NoArgsConstructor
public class Exercises {
    @Id
    private Integer id;
    private Integer workoutTYpeId;
    private String exerciseType;
    private Integer reps;
    private Integer weight;
    private Integer time;


}
