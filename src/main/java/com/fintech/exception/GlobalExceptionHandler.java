package com.fintech.exception;

import com.fintech.payload.ApisResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApisResponse<List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ApisResponse<List<String>> response = ApisResponse.error(errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApisResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        ApisResponse<String> response = ApisResponse.error(ex.getMessage(), status);
        return ResponseEntity.status(status).body(response);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApisResponse<String>> handleGenericException(Exception ex) {
//        ApisResponse<String> response = ApisResponse.error("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApisResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApisResponse<String> response = ApisResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
