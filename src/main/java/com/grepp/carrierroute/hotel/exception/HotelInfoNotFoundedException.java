package com.grepp.carrierroute.hotel.exception;

public class HotelInfoNotFoundedException extends RuntimeException{
    public HotelInfoNotFoundedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
