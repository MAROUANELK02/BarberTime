package com.barbertime.barbertime_backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ResponseException {
    private final String message;
    private final HttpStatus status;
}
