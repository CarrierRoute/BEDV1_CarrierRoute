package com.grepp.carrierroute.exception;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final Class<?> clazz;
    private final String id;

    public NotFoundException(Class<?> clazz, Long id) {
        this(clazz, String.valueOf(id));
    }

    public NotFoundException(Class<?> clazz, String id) {
        super(ExceptionMessageUtils.getMessage("exception.not_found_exception"));
        this.clazz = clazz;
        this.id = id;
    }
}
