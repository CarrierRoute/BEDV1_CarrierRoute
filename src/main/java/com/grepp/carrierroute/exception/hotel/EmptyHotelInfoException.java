package com.grepp.carrierroute.exception.hotel;

public class EmptyHotelInfoException extends RuntimeException{
    public EmptyHotelInfoException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
