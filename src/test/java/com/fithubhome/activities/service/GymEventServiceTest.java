package com.fithubhome.activities.service;

import com.fithubhome.activities.repository.GymEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GymEventServiceTest {

    GymEventService gymEventService;
    @Mock
    GymEventRepository gymEventRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        gymEventService = new GymEventService(gymEventRepository);
    }

//    @Test
//    void createGymEvent() throws EventsAreOverlappingException {
//        List<GymEvent> events = new ArrayList<>();
//        GymEvent event = new GymEvent();
//        event.setId((long) 1);
//        event.setEventType("Push Ups");
//        event.setDate(Date.valueOf(LocalDate.now()));
//        event.setOrganizerId((long) 1);
//        event.setMaxParticipants(12);
//        event.setStartTime(new Time(1030));
//        event.setEndTime(new Time(1200));
//        event.setWorkoutType(new ArrayList<>());
//
//        events.add(event);
//
//        when(gymEventRepository.getEventsByDate(Date.valueOf(LocalDate.now()))).thenReturn(events);
//        event.setId(2L);
//        GymEvent orUpdateGymEvent = gymEventService.createOrUpdateGymEvent(event);
//
//
//    }
}