package com.techstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 200)
    String name;

    @NotBlank(message = "SKU is required")
    @Size(max = 50)
    String sku;

    @Size(max = 2000)
    String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    BigDecimal price;

    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock must be greater than or equal to 0")
    Integer stock;

    @Size(max = 500)
    String imageUrl;

    @NotNull(message = "Active status is required")
    Boolean active;

    @NotNull(message = "Category is required")
    @Positive(message = "Category id must be greater than 0")
    Long categoryId;
}