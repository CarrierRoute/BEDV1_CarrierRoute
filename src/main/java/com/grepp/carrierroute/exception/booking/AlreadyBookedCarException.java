package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class AlreadyBookedCarException extends RuntimeException{

    private final Long bookingId;
    private final String message;

    public AlreadyBookedCarException(Long bookingId) {
        this.bookingId = bookingId;
        message = ExceptionMessageUtils.getMessage(this.getClass().getSimpleName());
    }
}
