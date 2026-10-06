package com.techstore.service;

import com.techstore.dto.request.ProductRequest;
import com.techstore.dto.response.ProductResponse;
import java.util.List;

public interface ProductService {

  List<ProductResponse> getAllProducts();

  ProductResponse getProductById(Long id);

  ProductResponse createProduct(ProductRequest request);

  ProductResponse updateProduct(Long id, ProductRequest request);

  void deleteProduct(Long id);
}
