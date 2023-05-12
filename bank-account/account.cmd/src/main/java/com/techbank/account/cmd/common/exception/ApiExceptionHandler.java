package com.techbank.account.cmd.common.exception;

import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({AggregateNotFoundException.class})
    public void handleAggregateNotFound() {

    }
}
