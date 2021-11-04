package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

import java.util.Locale;

@Getter
public class CancellationNotAllowedException extends RuntimeException {
    private final Class<?> clazz;
    private final Long id;

    public CancellationNotAllowedException(Class<?> clazz, Long id) {
        super(new StringBuilder()
                .append(clazz.getSimpleName())
                .append(" - ")
                .append(ExceptionMessageUtils.getMessage("exception.cancellation_not_allowedn")).toString()
        );

        this.clazz = clazz;
        this.id = id;
    }
}
