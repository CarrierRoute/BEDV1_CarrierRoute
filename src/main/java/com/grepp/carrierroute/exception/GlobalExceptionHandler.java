package com.grepp.carrierroute.exception;

import com.grepp.carrierroute.exception.booking.AlreadyBookedCarException;
import com.grepp.carrierroute.exception.booking.LackOfPointException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleNotFound(NotFoundException exception) {
        log.warn("{} Not Found. Id : {}", exception.getClazz().getSimpleName(), exception.getId());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleAlreadyBookedCar(AlreadyBookedCarException exception) {
        log.warn("Already Booked Car. Booking Id : {}", exception.getBookingId());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleLackOfPoint(LackOfPointException exception) {
        log.warn("Lack of Point. Current Point : {}", exception.getPoint());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.warn("Method Argument Not Valid.");
        Map<String, String> errors = new ConcurrentHashMap<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(
                        error -> putError(error, errors)
                );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private void putError(ObjectError error, Map<String, String> errors) {
        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
    }
}
