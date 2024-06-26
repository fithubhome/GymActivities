package com.fithubhome.activities.service;

import com.fithubhome.activities.exceptions.EventNotFoundException;
import com.fithubhome.activities.exceptions.ParticipantIsNotRegisteredToEvent;
import com.fithubhome.activities.exceptions.ParticipantIsRegisteredAlready;
import com.fithubhome.activities.model.GymEvent;
import com.fithubhome.activities.model.Participant;
import com.fithubhome.activities.repository.GymEventRepository;
import com.fithubhome.activities.repository.ParticipantsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipantService {
    ParticipantsRepository participantsRepository;
    GymEventRepository gymEventRepository;

    public ParticipantService(ParticipantsRepository participantsRepository, GymEventRepository gymEventRepository) {
        this.participantsRepository = participantsRepository;
        this.gymEventRepository = gymEventRepository;
    }

    public List<Participant> getParticipantsForEvent(Integer eventId) throws EventNotFoundException {
        Optional<GymEvent> gymEvent = gymEventRepository.findById(eventId);

        if (gymEvent.isPresent()) {
            return participantsRepository.getParticipantsForEvent(gymEvent.get());
        } else {
            throw new EventNotFoundException("The event you are searching for was not found");
        }

    }

    @Transactional
    public Participant signUpForTheEvent(Participant participant) throws ParticipantIsRegisteredAlready {
        Optional<Participant> alreadyRegisteredParticipant = getParticipantForEventFromDb(participant);
        if (alreadyRegisteredParticipant.isPresent()) {
            throw new ParticipantIsRegisteredAlready("Participant is already registered to this event");
        } else {
            return participantsRepository.save(participant);
        }
    }

    public void withdrawFromEvent(Participant participant) throws ParticipantIsNotRegisteredToEvent {
        Optional<Participant> alreadyRegisteredParticipant = getParticipantForEventFromDb(participant);

        if (alreadyRegisteredParticipant.isPresent()) {
            participantsRepository.delete(alreadyRegisteredParticipant.get());
        } else {
            throw new ParticipantIsNotRegisteredToEvent("Participant is not registered to this event");
        }
    }

    private Optional<Participant> getParticipantForEventFromDb(Participant participant) {
        return participantsRepository.
            getParticipantsForEvent(participant.getEvent())
            .stream()
            .filter(p -> Objects.equals(p.getProfileId(), participant.getProfileId()))
            .findAny();
    }
}
