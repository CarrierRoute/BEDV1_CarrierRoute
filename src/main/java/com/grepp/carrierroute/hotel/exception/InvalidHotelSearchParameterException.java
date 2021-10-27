package com.grepp.carrierroute.hotel.exception;

public class InvalidHotelSearchParameterException extends Exception{
    public InvalidHotelSearchParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
