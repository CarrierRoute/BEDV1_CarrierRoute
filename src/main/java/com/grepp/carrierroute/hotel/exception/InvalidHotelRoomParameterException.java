package com.grepp.carrierroute.hotel.exception;

public class InvalidHotelRoomParameterException extends Exception{
    public InvalidHotelRoomParameterException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
