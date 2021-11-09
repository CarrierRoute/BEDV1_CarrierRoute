package com.grepp.carrierroute.exception;

import com.grepp.carrierroute.exception.booking.*;
import com.grepp.carrierroute.exception.hotel.InvalidHotelParameterException;
import com.grepp.carrierroute.exception.hotel.InvalidHotelRoomParameterException;
import com.grepp.carrierroute.exception.hotel.InvalidHotelSearchParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    @ExceptionHandler({InvalidHotelBookingParameterException.class,
                        InvalidHotelParameterException.class,
                        InvalidHotelRoomParameterException.class,
                        InvalidHotelSearchParameterException.class})
    public ResponseEntity<String> handleInvalidArgument(Exception exception){
        log.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleCancellationNotAllowed(CancellationNotAllowedException exception){
        log.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInsufficientRoom(InsufficentRoomException exception){
        log.warn(exception.getMessage());
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> constraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(extractErrorMessages(e),HttpStatus.BAD_REQUEST);
    }

    private List<String> extractErrorMessages(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}
