package com.grepp.carrierroute.exception.hotel;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class InvalidHotelSearchParameterException extends RuntimeException{
    public InvalidHotelSearchParameterException() {
        super(ExceptionMessageUtils.getMessage(InvalidHotelSearchParameterException.class.getSimpleName()));
    }
}
