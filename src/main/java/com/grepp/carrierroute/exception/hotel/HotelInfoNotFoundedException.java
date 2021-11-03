package com.grepp.carrierroute.exception.hotel;

public class HotelInfoNotFoundedException extends RuntimeException{
    public HotelInfoNotFoundedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
