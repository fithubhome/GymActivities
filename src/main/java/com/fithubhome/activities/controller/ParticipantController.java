package com.fithubhome.activities.controller;

import com.fithubhome.activities.exceptions.EventNotFoundException;
import com.fithubhome.activities.exceptions.EventsAreOverlappingException;
import com.fithubhome.activities.exceptions.ParticipantIsNotRegisteredToEvent;
import com.fithubhome.activities.exceptions.ParticipantIsRegisteredAlready;
import com.fithubhome.activities.model.Participant;
import com.fithubhome.activities.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    ParticipantService participantService;

    @GetMapping(params = "eventId")
    public ResponseEntity<List<Participant>> getParticipantsForEvent(@RequestParam Integer eventId) throws EventNotFoundException {

        List<Participant> participantsForEvent = participantService.getParticipantsForEvent(eventId);

        return ResponseEntity
                .status(200)
                .body(participantsForEvent);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Participant>> signUpForTheEvent(@RequestBody @Validated Participant participant)
            throws EventNotFoundException, ParticipantIsRegisteredAlready, EventsAreOverlappingException {

        Participant registeredParticipant = participantService.signUpForTheEvent(participant);

        return ResponseEntity
                .status(202)
                .body(EntityModel.of(
                        registeredParticipant,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder
                                        .methodOn(ParticipantController.class).withdrawFromEvent(registeredParticipant))
                                .withRel("DELETE")
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder
                                        .methodOn(GymEventController.class).getEventById(registeredParticipant.getEvent().getId()))
                                .withRel("GET")
                ));
    }

    @DeleteMapping()
    public ResponseEntity<String> withdrawFromEvent(@RequestBody Participant participant) {
        try {
            participantService.withdrawFromEvent(participant);
        } catch (ParticipantIsNotRegisteredToEvent exception) {
            ResponseEntity
                    .status(404)
                    .body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }

        return ResponseEntity
                .status(200)
                .body("User withdrew successfully");
    }
}
