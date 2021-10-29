package com.grepp.carrierroute.hotel.exception;

public class EmptyHotelInfoException extends RuntimeException{
    public EmptyHotelInfoException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
