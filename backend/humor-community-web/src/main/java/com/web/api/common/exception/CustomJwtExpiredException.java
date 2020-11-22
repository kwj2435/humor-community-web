package com.web.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
public class CustomJwtExpiredException extends RuntimeException{
    public CustomJwtExpiredException(String message) {
        super(message);
    }

    public CustomJwtExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
