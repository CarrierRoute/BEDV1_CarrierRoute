package com.grepp.carrierroute.booking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CarBookingExceptionCode {
    ALREADY_BOOKING_EXCEPTION("Already Booked Exception", HttpStatus.NOT_ACCEPTABLE),
    CARBOOKING_NOT_FOUND_EXCEPTION("CarBooking Not Found Exception", HttpStatus.NOT_FOUND),
    USER_AND_CARBOOKING_NOT_MATCH_EXCEPTION("User and CarBooking Not Match Exception", HttpStatus.BAD_REQUEST);

    private String value;
    private HttpStatus httpStatus;

    CarBookingExceptionCode(String value, HttpStatus httpStatus) {
        this.value = value;
        this.httpStatus = httpStatus;
    }
}
