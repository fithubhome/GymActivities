package com.fithubhome.activities.service;

import com.fithubhome.activities.exceptions.EventNotFoundException;
import com.fithubhome.activities.exceptions.EventsAreOverlappingException;
import com.fithubhome.activities.model.GymEvent;
import com.fithubhome.activities.repository.GymEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class GymEventService {
    GymEventRepository gymEventRepository;

    public GymEventService(GymEventRepository gymEventRepository) {
        this.gymEventRepository = gymEventRepository;
    }

    @Transactional
    public GymEvent createOrUpdateGymEvent(GymEvent event) throws EventsAreOverlappingException {

        List<GymEvent> eventsByDate = gymEventRepository.getEventsByDate(event.getDate())
                .stream()
                .filter(e -> Objects.equals(e.getOrganizerId(), event.getOrganizerId()))
                .filter(e -> !Objects.equals(e.getId(), event.getId()))
                .toList();

        if (eventsTimesAreOverlapping(event, eventsByDate)) {
            throw new EventsAreOverlappingException("Can not create the event because time is overlapping with other events");
        } else {
            return gymEventRepository.save(event);
        }

    }

    private boolean eventsTimesAreOverlapping(GymEvent event, List<GymEvent> eventsByDate) {
        boolean overlaps = false;
        for (GymEvent theEvent : eventsByDate) {
            overlaps = theEvent.getStartTime().before(event.getEndTime())
                    || theEvent.getEndTime().after(event.getStartTime());
        }

        return overlaps;
    }

    public List<GymEvent> getAllEvents() throws EventNotFoundException {

        List<GymEvent> allEvents = gymEventRepository.findAll();
        if (allEvents.isEmpty()) {
            throw new EventNotFoundException("No events are available currently!");
        } else {
            return allEvents;
        }

    }

    public GymEvent getEventById(Integer id) throws EventNotFoundException {
        return gymEventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with id: " + id + " was not found"));

    }

    public List<GymEvent> getEventByOrganizerId(UUID organizerId) throws EventNotFoundException {

        List<GymEvent> eventsByOrganizerId = gymEventRepository.getEventsByOrganizerId(organizerId);

        if (!eventsByOrganizerId.isEmpty()) {
            return eventsByOrganizerId;
        } else {
            throw new EventNotFoundException("No events were found for this Organizer");
        }
    }

    public void deleteEventById(Integer id) throws EventNotFoundException {
        Optional<GymEvent> gymEvent = gymEventRepository.findById(id);

        if (gymEvent.isPresent()) {
            gymEventRepository.delete(gymEvent.get());
        } else {
            throw new EventNotFoundException("Event with id: " + id + " was not found");
        }
    }
}
