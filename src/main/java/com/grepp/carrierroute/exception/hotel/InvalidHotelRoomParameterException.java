package com.grepp.carrierroute.exception.hotel;

public class InvalidHotelRoomParameterException extends RuntimeException{
    public InvalidHotelRoomParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
