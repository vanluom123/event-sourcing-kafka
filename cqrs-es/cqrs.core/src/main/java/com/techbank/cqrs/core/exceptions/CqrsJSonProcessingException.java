package com.techbank.cqrs.core.exceptions;

public class CqrsJSonProcessingException extends RuntimeException {
    public CqrsJSonProcessingException() {
    }

    public CqrsJSonProcessingException(String message) {
        super(message);
    }

    public CqrsJSonProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CqrsJSonProcessingException(Throwable cause) {
        super(cause);
    }
}
