package com.fithubhome.activities.controller;

import com.fithubhome.activities.exceptions.EventNotFoundException;
import com.fithubhome.activities.exceptions.EventsAreOverlappingException;
import com.fithubhome.activities.exceptions.ParticipantIsRegisteredAlready;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFoundException(EventNotFoundException exception) {
        return ResponseEntity
                .status(404)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EventsAreOverlappingException.class)
    public ResponseEntity<String> handleEventAreOverlappingException(EventsAreOverlappingException exception) {
        return ResponseEntity
                .status(409)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ParticipantIsRegisteredAlready.class)
    public ResponseEntity<String> handleParticipantIsRegisteredAlreadyException(ParticipantIsRegisteredAlready exception) {
        return ResponseEntity
                .status(409)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException() {
        return ResponseEntity
                .internalServerError()
                .body("An unexpected error occurred. \n" + System.currentTimeMillis());
    }
}
