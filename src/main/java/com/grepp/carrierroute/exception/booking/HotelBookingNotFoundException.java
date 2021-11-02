package com.grepp.carrierroute.booking.exception;

import com.grepp.carrierroute.hotel.exception.ErrorMessage;

public class HotelBookingNotFoundException extends RuntimeException{
    public HotelBookingNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
