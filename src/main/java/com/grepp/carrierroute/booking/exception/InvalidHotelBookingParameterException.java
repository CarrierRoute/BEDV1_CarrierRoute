package com.grepp.carrierroute.booking.exception;

import com.grepp.carrierroute.hotel.exception.ErrorMessage;

public class InvalidHotelBookingParameterException extends RuntimeException{
    public InvalidHotelBookingParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
