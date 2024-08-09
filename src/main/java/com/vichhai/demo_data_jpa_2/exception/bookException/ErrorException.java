package com.vichhai.demo_data_jpa_2.exception.bookException;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ErrorException {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e){
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getMessage());
        response.setStatus(e.status);
        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(e.status)));
    }

    @Data
    public static class ErrorResponse {
        private String error;
        private int status;
        private LocalDateTime timestamp = LocalDateTime.now();
    }
}
