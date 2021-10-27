package com.grepp.carrierroute.hotel.exception;

public class HotelInfoNotFoundedException extends Exception{
    public HotelInfoNotFoundedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
