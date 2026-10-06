package com.techstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(
            9999,
            "Uncategorized error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    INVALID_REQUEST(
            1001,
            "Invalid request",
            HttpStatus.BAD_REQUEST
    ),

    PRODUCT_NOT_FOUND(
            2001,
            "Product not found",
            HttpStatus.NOT_FOUND
    ),

    PRODUCT_SKU_EXISTED(
            2002,
            "Product SKU already exists",
            HttpStatus.CONFLICT
    ),

    CATEGORY_NOT_FOUND(
            3001,
            "Category not found",
            HttpStatus.NOT_FOUND
    );

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(
            int code,
            String message,
            HttpStatus httpStatus
    ) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}