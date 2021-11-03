package com.grepp.carrierroute.exception.hotel;

import com.grepp.carrierroute.util.ExceptionMessageUtils;

public class InvalidHotelRoomParameterException extends RuntimeException{
    public InvalidHotelRoomParameterException() {
        super(ExceptionMessageUtils.getMessage("exception.invalid_hotelroom_parameter"));
    }
}
