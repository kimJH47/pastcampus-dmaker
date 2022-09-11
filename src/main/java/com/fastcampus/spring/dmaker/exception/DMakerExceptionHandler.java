package com.fastcampus.spring.dmaker.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
@Slf4j
public class DMakerExceptionHandler {

    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handleException(DMakerException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getDMakerErrorCode(), request.getRequestURL(), e.getDetailedMessage());
        return DMakerErrorResponse.builder()
                                  .errorCode(e.getDMakerErrorCode())
                                  .errorMessage(e.getDetailedMessage())
                                  .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public DMakerErrorResponse handleBadRequest(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURL(), e.getMessage());
        return DMakerErrorResponse.builder()
                                  .errorCode(DMakerErrorCode.INVALID_REQUEST)
                                  .errorMessage(e.getMessage())
                                  .build();
    }
    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURL(), e.getMessage());
        return DMakerErrorResponse.builder()
                                  .errorCode(DMakerErrorCode.INVALID_REQUEST)
                                  .errorMessage(DMakerErrorCode.INTERNAL_SERVER_ERROR.getDetailedMassage())
                                  .build();
    }

}
