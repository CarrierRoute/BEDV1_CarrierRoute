package com.grepp.carrierroute.exception.booking;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class CancellationNotAllowedException extends RuntimeException {
    private final Class<?> clazz;
    private final String id;

    public CancellationNotAllowedException(Class<?> clazz, Long id) {
        this(clazz, String.valueOf(id));
    }

    public CancellationNotAllowedException(Class<?> clazz, String id) {
        super(ExceptionMessageUtils.getMessage(clazz.getSimpleName() + CancellationNotAllowedException.class.getSimpleName()));
        this.clazz = clazz;
        this.id = id;
    }
}
