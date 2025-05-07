package org.example.controlleradvice;

import org.example.exception.BadDataException;
import org.example.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<SimpleResponse> handleNotFoundException(NotFoundException ex) {
        SimpleResponse simpleResponse = new SimpleResponse(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(simpleResponse);
    }

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<SimpleResponse> handleBadDataException(BadDataException ex) {
        SimpleResponse simpleResponse = new SimpleResponse(ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(simpleResponse);
    }

}

