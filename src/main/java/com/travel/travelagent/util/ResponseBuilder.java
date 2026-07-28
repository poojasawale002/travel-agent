package com.travel.travelagent.util;

import org.springframework.http.ResponseEntity;

import com.travel.travelagent.dto.ApiResponse;

public class ResponseBuilder {

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {

        ApiResponse<T> response =
                new ApiResponse<>(true, message, data);

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {

        ApiResponse<T> response =
                new ApiResponse<>(true, message, data);

        return ResponseEntity.status(201).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String message) {

        ApiResponse<T> response =
                new ApiResponse<>(false, message, null);

        return ResponseEntity.badRequest().body(response);
    }

}