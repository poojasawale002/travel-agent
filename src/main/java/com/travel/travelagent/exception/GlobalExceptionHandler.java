package com.travel.travelagent.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.travel.travelagent.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleValidation(
            MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(),
                                   error.getDefaultMessage()));

        ApiResponse<Map<String,String>> response =
                new ApiResponse<>(
                        false,
                        "Validation Failed",
                        errors);

        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleTripNotFound(
            TripNotFoundException ex){

        ApiResponse<String> response =
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFound(
            UserNotFoundException ex){

        ApiResponse<String> response =
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex){

        ApiResponse<String> response =
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}