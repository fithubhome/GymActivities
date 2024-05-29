package com.fithubhome.activities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "gym_event")
public class GymEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_description")
    private String eventDescription;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_type_id", nullable = false)
    private List<WorkoutType> workoutType;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "start_time", nullable = false)
    private Time startTime;
    @Column(name = "end_time", nullable = false)
    private Time endTime;
    @Column(name = "organizer_id", nullable = false)
    private Long organizerId;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "max_participants")
    private Integer maxParticipants;

}
