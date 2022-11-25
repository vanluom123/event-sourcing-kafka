package com.techbank.cqrs.core.exceptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionData {
    private String message;
    private Throwable cause;
    private HttpStatus statusCode;
    private String type;
}
