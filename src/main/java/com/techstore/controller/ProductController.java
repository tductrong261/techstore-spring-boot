package com.techstore.controller;

import com.techstore.dto.request.ProductRequest;
import com.techstore.dto.response.ApiResponse;
import com.techstore.dto.response.ProductResponse;
import com.techstore.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

  ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
    var result = productService.createProduct(request);

    return ApiResponse.<ProductResponse>builder().data(result).build();
  }

  @GetMapping
  public ApiResponse<List<ProductResponse>> getAllProducts() {
    var result = productService.getAllProducts();

    return ApiResponse.<List<ProductResponse>>builder().data(result).build();
  }

  @GetMapping("/{id}")
  public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
    var result = productService.getProductById(id);

    return ApiResponse.<ProductResponse>builder().data(result).build();
  }

  @PutMapping("/{id}")
  public ApiResponse<ProductResponse> updateProduct(
      @PathVariable Long id, @Valid @RequestBody ProductRequest request) {
    var result = productService.updateProduct(id, request);

    return ApiResponse.<ProductResponse>builder().data(result).build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);

    return ApiResponse.<Void>builder().build();
  }
}
