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
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Date;
import java.sql.Time;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "gym_event")
public class GymEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String eventName;
    private String eventDescription;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_type_id", nullable = false)
    private List<WorkoutType> workoutType;
    private Date date;
    private Time startTime;
    private Time endTime;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID organizerId;
    private String eventType;
    private Integer maxParticipants;

}
