package com.tui.mobile.exception.handler;

import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ExceptionBody> handleFeignNotFoundException(
            FeignException.NotFound exception) {

        ExceptionBody exceptionBody = ExceptionBody.builder()
                .status(NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(exceptionBody, NOT_FOUND);
    }


    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ExceptionBody> handleHttpMediaTypeNotAcceptableException(
            HttpMediaTypeNotAcceptableException exception) {

        ExceptionBody exceptionBody = ExceptionBody.builder()
                .status(NOT_ACCEPTABLE.value())
                .message(exception.getMessage())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(exceptionBody, headers, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionBody> handleException(Exception exception) {

        ExceptionBody exceptionBody = ExceptionBody.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .message("Something went wrong during request processing")
                .build();

        return new ResponseEntity<>(exceptionBody, INTERNAL_SERVER_ERROR);
    }
}
