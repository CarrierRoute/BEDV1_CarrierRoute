package com.grepp.carrierroute.hotel.exception;

public class InvalidHotelRoomParameterException extends RuntimeException{
    public InvalidHotelRoomParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
