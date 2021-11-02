package com.grepp.carrierroute.booking.exception;

import com.grepp.carrierroute.hotel.exception.ErrorMessage;

public class InsufficentRoomException extends RuntimeException{
    public InsufficentRoomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
