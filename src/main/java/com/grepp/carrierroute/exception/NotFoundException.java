package com.grepp.carrierroute.exception;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final Class<?> clazz;
    private final String id;
    private String message;

    public NotFoundException(Class<?> clazz, Long id) {
        this(clazz, String.valueOf(id));
    }

    public NotFoundException(Class<?> clazz, String id) {
        this.clazz = clazz;
        this.id = id;
        message = ExceptionMessageUtils.getMessage(clazz.getSimpleName() + this.getClass().getSimpleName());
    }
}
