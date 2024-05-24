package com.fithubhome.activities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "profile_id", nullable = false)
    private Long profileId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    private GymEvent event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public GymEvent getEvent() {
        return event;
    }

    public void setEvent(GymEvent event) {
        this.event = event;
    }
}
