package com.fithubhome.activities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
