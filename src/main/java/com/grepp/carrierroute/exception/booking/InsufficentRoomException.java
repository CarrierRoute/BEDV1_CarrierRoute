package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class InsufficentRoomException extends RuntimeException{
    public InsufficentRoomException() {
        super(ExceptionMessageUtils.getMessage(InsufficentRoomException.class.getSimpleName()));
    }
}
