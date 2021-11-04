package com.grepp.carrierroute.exception.hotel;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class EmptyHotelInfoException extends RuntimeException{
    public EmptyHotelInfoException() {
        super(ExceptionMessageUtils.getMessage("exception.empty_hotel_info"));
    }
}
