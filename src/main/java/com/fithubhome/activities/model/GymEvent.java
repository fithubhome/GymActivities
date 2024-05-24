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

import java.sql.Date;
import java.sql.Time;
import java.util.List;


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


    public Long getId() {
        return id;
    }

    public List<WorkoutType> getWorkoutType() {
        return workoutType;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public String getEventType() {
        return eventType;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWorkoutType(List<WorkoutType> workoutType) {
        this.workoutType = workoutType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
