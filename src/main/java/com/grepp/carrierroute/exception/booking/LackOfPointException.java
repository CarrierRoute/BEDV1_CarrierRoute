package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class LackOfPointException extends RuntimeException {

    private final Long point;

    public LackOfPointException(Long point) {
        super(ExceptionMessageUtils.getMessage("exception.lack_of_point", new Object[]{point}));
        this.point = point;
    }
}
