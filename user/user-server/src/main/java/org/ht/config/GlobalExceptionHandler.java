package org.ht.config;

import org.ht.model.response.Resp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Resp<?> handleException(Exception e) {
        return Resp.fail(e.getMessage());
    }
} 