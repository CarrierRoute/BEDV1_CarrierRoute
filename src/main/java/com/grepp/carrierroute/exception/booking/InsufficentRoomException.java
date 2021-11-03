package com.grepp.carrierroute.exception.booking;


import com.grepp.carrierroute.exception.hotel.ErrorMessage;

public class InsufficentRoomException extends RuntimeException{
    public InsufficentRoomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
