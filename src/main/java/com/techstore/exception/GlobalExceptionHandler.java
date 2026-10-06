package com.techstore.exception;

import com.techstore.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(
            AppException exception
    ) {

        ErrorCode errorCode =
                exception.getErrorCode();

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(
            MethodArgumentNotValidException exception
    ) {

        Map<String, String> errors =
                exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        error -> error.getField(),
                                        error -> error.getDefaultMessage(),
                                        (first, second) -> first
                                )
                        );

        ApiResponse<Map<String, String>> response =
                ApiResponse.<Map<String, String>>builder()
                        .code(
                                ErrorCode.INVALID_REQUEST.getCode()
                        )
                        .message(
                                ErrorCode.INVALID_REQUEST.getMessage()
                        )
                        .data(errors)
                        .build();

        return ResponseEntity
                .status(
                        ErrorCode.INVALID_REQUEST.getHttpStatus()
                )
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception exception
    ) {

        ErrorCode errorCode =
                ErrorCode.UNCATEGORIZED_EXCEPTION;

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }
}