package com.chennai.beerorderservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class BeerOrderServiceExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String handlException(DataNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ex.getMessage();
    }
}
