package org.palad.fakeshop.controller;

import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.dto.exception.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "org.palad.fakeshop")
@Log4j2
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDTO illegalHandle(IllegalArgumentException e) {
        log.error( "[IllegalArgumentException] " + e.getMessage());
        return new ErrorDTO("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ErrorDTO numberFormatHandler(NumberFormatException e) {
        log.error("[numberFormatException] " + e.getMessage());
        return new ErrorDTO("BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDTO numberFormatHandler(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException" + e.getMessage());
        return new ErrorDTO("BAD_REQUEST", e.getMessage());
    }

}
