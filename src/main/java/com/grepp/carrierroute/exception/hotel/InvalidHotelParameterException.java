package com.grepp.carrierroute.exception.hotel;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class InvalidHotelParameterException extends RuntimeException{
    public InvalidHotelParameterException() {
        super(ExceptionMessageUtils.getMessage(InvalidHotelParameterException.class.getSimpleName()));
    }
}
