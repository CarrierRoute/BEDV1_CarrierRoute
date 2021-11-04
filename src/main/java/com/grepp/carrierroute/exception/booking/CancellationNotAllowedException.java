package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class CancellationNotAllowedException extends RuntimeException {
    private final Class<?> clazz;
    private final Long id;

    public CancellationNotAllowedException(Class<?> clazz, Long id) {
        super(ExceptionMessageUtils.getMessage(clazz.getSimpleName() + CancellationNotAllowedException.class.getSimpleName()));
        this.clazz = clazz;
        this.id = id;
    }
}
