package com.grepp.carrierroute.exception.hotel;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class EmptyHotelInfoException extends RuntimeException{
    public EmptyHotelInfoException() {
        super(ExceptionMessageUtils.getMessage(EmptyHotelInfoException.class.getSimpleName()));
    }
}
