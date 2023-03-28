package org.palad.fakeshop.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.palad.fakeshop.dto.exception.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "org.palad.fakeshop")
@Log4j2
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDTO illegalHandle(IllegalArgumentException e) {
        log.error("[IllegalArgumentException] " + e.getMessage());
        return new ErrorDTO("BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ErrorDTO numberFormatHandler(NumberFormatException e) {
        log.error("[numberFormatException] " + e.getMessage());
        return new ErrorDTO("BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDTO httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException" + e.getMessage());
        return new ErrorDTO("BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDTO notFoundException(NotFoundException e) {
        log.error("UserNotFoundException" + e.getMessage());
        return new ErrorDTO("NOT_FOUND", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ErrorDTO> bindException(BindException e) {
        log.error("bindException" + e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<ErrorDTO> errors = bindingResult.getAllErrors()
                .stream()
                .map(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getCode(), error.getDefaultMessage());
                    return errorDTO;
                }).collect(Collectors.toList());

        return errors;
    }

}
