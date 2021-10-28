package com.grepp.carrierroute.hotel.exception;

public class InvalidHotelSearchParameterException extends RuntimeException{
    public InvalidHotelSearchParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
