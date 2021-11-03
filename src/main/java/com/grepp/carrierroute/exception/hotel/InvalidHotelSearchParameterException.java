package com.grepp.carrierroute.exception.hotel;

public class InvalidHotelSearchParameterException extends RuntimeException{
    public InvalidHotelSearchParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
