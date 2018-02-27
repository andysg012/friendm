package com.example.friendm.friend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FriendResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(FriendResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
             HttpHeaders headers,
             HttpStatus status,
             WebRequest request) {

        logger.info("=== handleHttpRequestMethodNotSupported {}", ex.getMessage());

        String responseBody = "HttpRequestMethodNotSupportedException: " + ex.getMessage() ;
        return handleExceptionInternal(ex, responseBody, headers, status, request);
    }
}
