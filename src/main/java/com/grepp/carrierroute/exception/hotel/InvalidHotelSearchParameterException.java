package com.grepp.carrierroute.exception.hotel;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class InvalidHotelSearchParameterException extends RuntimeException{
    public InvalidHotelSearchParameterException() {
        super(ExceptionMessageUtils.getMessage("exception.invalid_hotel_search_parameter"));
    }
}
