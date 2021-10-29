package com.grepp.carrierroute.booking.exception;

public class AlreadyBookedException extends RuntimeException{

    public AlreadyBookedException(String message) {
        super(message);
    }
}
