package com.techstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductResponse {
     Long id;
     String name;
     String sku;
     String description;
     BigDecimal price;
     Integer stock;
     String imageUrl;
     Boolean active;
     Long categoryId;
     String categoryName;
}