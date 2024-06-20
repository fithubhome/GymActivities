package com.fithubhome.activities.controller;

import com.fithubhome.activities.exceptions.EventNotFoundException;
import com.fithubhome.activities.exceptions.EventsAreOverlappingException;
import com.fithubhome.activities.exceptions.ParticipantIsRegisteredAlready;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFoundException(EventNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity
                .status(404)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EventsAreOverlappingException.class)
    public ResponseEntity<String> handleEventAreOverlappingException(EventsAreOverlappingException exception) {
        log.error(exception.getMessage());
        return ResponseEntity
                .status(409)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ParticipantIsRegisteredAlready.class)
    public ResponseEntity<String> handleParticipantIsRegisteredAlreadyException(ParticipantIsRegisteredAlready exception) {
        log.error(exception.getMessage());
        return ResponseEntity
                .status(409)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity
                .internalServerError()
                .body("An unexpected error occurred. \n" + System.currentTimeMillis());
    }
}
