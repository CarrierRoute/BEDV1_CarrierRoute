package com.grepp.carrierroute.car.exception.handler;

import com.grepp.carrierroute.car.exception.CarCompanyNotFoundException;
import com.grepp.carrierroute.car.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.grepp.carrierroute.car")
public class CarExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleCarNotFound(CarNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleCarCompanyNotFound(CarCompanyNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
