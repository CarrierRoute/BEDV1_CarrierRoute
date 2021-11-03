package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class AlreadyBookedCarException extends RuntimeException{

    private final Long bookingId;

    public AlreadyBookedCarException(Long bookingId) {
        super(ExceptionMessageUtils.getMessage(AlreadyBookedCarException.class.getSimpleName()));
        this.bookingId = bookingId;
    }
}
