package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class InvalidHotelBookingParameterException extends RuntimeException{
    public InvalidHotelBookingParameterException() {
        super(ExceptionMessageUtils.getMessage("exception.invalid_hotelbooking_parameter"));
    }
}
