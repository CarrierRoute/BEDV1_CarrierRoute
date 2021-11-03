package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.exception.hotel.ErrorMessage;

public class InvalidHotelBookingParameterException extends RuntimeException{
    public InvalidHotelBookingParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
