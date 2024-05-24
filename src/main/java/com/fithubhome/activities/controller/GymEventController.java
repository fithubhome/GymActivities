package com.fithubhome.activities.controller;

import com.fithubhome.activities.exceptions.EventNotFoundException;
import com.fithubhome.activities.exceptions.EventsAreOverlappingException;
import com.fithubhome.activities.model.GymEvent;
import com.fithubhome.activities.service.GymEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/event")
@RestController
@Slf4j
@Validated
public class GymEventController {
    @Autowired
    GymEventService gymEventService;


    @GetMapping("/all-events")
    public ResponseEntity<List<GymEvent>> getAllEvents() {

        List<GymEvent> allEvents;
        try {
            allEvents = gymEventService.getAllEvents();
        } catch (EventNotFoundException exception) {
            return ResponseEntity
                    .status(404)
                    .body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }

        return ResponseEntity
                .ok()
                .body(allEvents);
    }

    @GetMapping(params = "eventId")
    public ResponseEntity<EntityModel<GymEvent>> getEventById(@RequestParam Long eventId) {

        GymEvent eventById;
        try {
            eventById = gymEventService.getEventById(eventId);
        } catch (EventNotFoundException exception) {
            return ResponseEntity
                    .status(404)
                    .body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }

        return ResponseEntity
                .status(200)
                .body(EntityModel.of(
                        eventById,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).getEventById(eventById.getId()))
                                .withRel(LinkRelation.of("GET"))
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).updateEvent(eventById))
                                .withRel("PUT")
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).deleteEventById(eventById.getId()))
                                .withRel("DELETE")));
    }

    @GetMapping(params = "organizerId")
    public ResponseEntity<List<GymEvent>> getEventByOrganizerId(@RequestParam Long organizerId) {

        List<GymEvent> organizerEvents;
        try {
            organizerEvents = gymEventService.getEventByOrganizerId(organizerId);
        } catch (EventNotFoundException exception) {
            return ResponseEntity
                    .status(404)
                    .body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }

        return ResponseEntity
                .status(200)
                .body(organizerEvents);
    }

    @PostMapping
    public ResponseEntity<EntityModel<GymEvent>> createEvent(@RequestBody @Validated GymEvent event) {

        GymEvent createdEvent;
        try {
            createdEvent = gymEventService.createOrUpdateGymEvent(event);
        } catch (EventsAreOverlappingException exception) {
            return ResponseEntity
                    .status(409)
                    .body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }

        return ResponseEntity
                .status(201)
                .body(EntityModel.of(
                        createdEvent,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).getEventById(createdEvent.getId()))
                                .withRel("GET")
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).updateEvent(createdEvent))
                                .withRel("PUT")
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).deleteEventById(createdEvent.getId()))
                                .withRel("DELETE")));
    }

    @PutMapping
    public ResponseEntity<EntityModel<GymEvent>> updateEvent(@RequestBody @Validated GymEvent event) {

        GymEvent updatedEvent;
        try {
            updatedEvent = gymEventService.createOrUpdateGymEvent(event);
        } catch (EventsAreOverlappingException exception) {
            return ResponseEntity
                    .status(409)
                    .body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }

        return ResponseEntity
                .status(202)
                .body(EntityModel.of(
                        updatedEvent,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).getEventById(updatedEvent.getId()))
                                .withRel("GET")
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).updateEvent(updatedEvent))
                                .withRel("PUT")
                        , WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(GymEventController.class).deleteEventById(updatedEvent.getId()))
                                .withRel("DELETE")));
    }

    @DeleteMapping(params = "eventId")
    public ResponseEntity<String> deleteEventById(@RequestParam Long eventId) {

        try {
            gymEventService.deleteEventById(eventId);
        } catch (EventNotFoundException exception) {
            return ResponseEntity
                    .status(404)
                    .body(null);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
        return ResponseEntity
                .status(200)
                .body("Event deleted successfully");
    }
}
