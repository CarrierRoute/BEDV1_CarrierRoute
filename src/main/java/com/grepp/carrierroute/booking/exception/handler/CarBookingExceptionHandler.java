package com.grepp.carrierroute.booking.exception.handler;

import com.grepp.carrierroute.booking.exception.AlreadyBookedException;
import com.grepp.carrierroute.booking.exception.CarBookingExceptionCode;
import com.grepp.carrierroute.booking.exception.CarBookingNotFoundException;
import com.grepp.carrierroute.booking.exception.UserAndCarBookingNotMatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.grepp.carrierroute.booking")
public class CarBookingExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleAlreadyBooked(AlreadyBookedException exception) {
        return new ResponseEntity<>(exception.getMessage(), CarBookingExceptionCode.ALREADY_BOOKING_EXCEPTION.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleCarBookingNotFound(CarBookingNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), CarBookingExceptionCode.CARBOOKING_NOT_FOUND_EXCEPTION.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleUserAndCarBookingNotMatch(UserAndCarBookingNotMatchException exception) {
        return new ResponseEntity<>(exception.getMessage(), CarBookingExceptionCode.USER_AND_CARBOOKING_NOT_MATCH_EXCEPTION.getHttpStatus());
    }
}
