package com.example.BusRouteAPIv1.exception;

import com.example.BusRouteAPIv1.utility.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class ExceptionHandling {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<HttpResponse> emptyFieldException(EmptyFieldException exception){
        return createHttpResponse(NOT_ACCEPTABLE, exception.getMessage());
    }

    @ExceptionHandler(WhiteSpaceException.class)
    public ResponseEntity<HttpResponse> whiteSpaceException(WhiteSpaceException exception){
        return createHttpResponse(NOT_ACCEPTABLE, exception.getMessage());
    }

    @ExceptionHandler(GeneralUsageException.class)
    public ResponseEntity<HttpResponse> GeneralUsageException(GeneralUsageException exception){
        return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    // template to show bad request responses to users
    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        HttpResponse httpResponse = new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message);
        return new ResponseEntity<>(httpResponse, httpStatus);
    }
}
