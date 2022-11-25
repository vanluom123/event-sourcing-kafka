package com.techbank.account.cmd.common.exception;

import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.techbank.cqrs.core.exceptions.ExceptionData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({AggregateNotFoundException.class})
    public ResponseEntity<ExceptionData> handleAggregateNotFoundException(AggregateNotFoundException e) {
        var exceptionData = ExceptionData.builder()
                .message(e.getMessage())
                .cause(e)
                .statusCode(HttpStatus.NOT_FOUND)
                .type("Aggregate not found")
                .build();
        return new ResponseEntity<>(exceptionData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ExceptionData> handleResourceNotFoundException(ResourceNotFoundException e) {
        var exceptionData = ExceptionData.builder()
                .message(e.getMessage())
                .cause(e)
                .statusCode(HttpStatus.NOT_FOUND)
                .type("Resource not found")
                .build();
        return new ResponseEntity<>(exceptionData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<ExceptionData> handleIllegalStateException(IllegalStateException e) {
        var exceptionData = ExceptionData.builder()
                .message(e.getMessage())
                .cause(e)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .type("Illegal state")
                .build();
        return new ResponseEntity<>(exceptionData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
