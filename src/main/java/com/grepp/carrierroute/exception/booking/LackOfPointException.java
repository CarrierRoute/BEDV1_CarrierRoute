package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class LackOfPointException extends RuntimeException {

    private final Long point;
    private final String message;

    public LackOfPointException(Long point) {
        this.point = point;
        this.message = ExceptionMessageUtils.getMessage(this.getClass().getSimpleName(), new Object[]{point});
    }
}
