package com.ssg.newbie.web;

import com.ssg.newbie.domain.ErrorResponse;
import com.ssg.newbie.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("[handleException] url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return new ErrorResponse("INTERNAL_ERROR", e.getMessage());
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(Exception e, HttpServletRequest request) {
        log.error("[handleBadRequest] url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return new ErrorResponse("INVALID_REQUEST", e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(Exception e, HttpServletRequest request) {
        log.error("[handleNotFound] url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return new ErrorResponse("NOT_FOUND", e.getMessage());
    }
}
