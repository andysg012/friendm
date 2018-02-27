package com.example.friendm.friend;

import com.example.friendm.json.JsonExceptionResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FriendResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(FriendResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        logger.info("=== handleExceptionInternal {}", ex.getMessage());

        JsonExceptionResponse jsonResponse = JsonExceptionResponse.exception(ex.getMessage());

		return super.handleExceptionInternal(ex, jsonResponse, headers, status, request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        JsonExceptionResponse jsonResponse = JsonExceptionResponse.exception(ex.getMessage());

        return new ResponseEntity<Object>(jsonResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
