package com.techstore.service;

import com.techstore.dto.request.CategoryRequest;
import com.techstore.dto.response.CategoryResponse;
import java.util.List;

public interface CategoryService {

  CategoryResponse createCategory(
          CategoryRequest request
  );

  List<CategoryResponse> getAllCategories();

  CategoryResponse getCategoryById(
          Long id
  );

  CategoryResponse updateCategory(
          Long id,
          CategoryRequest request
  );

  void deleteCategory(
          Long id
  );
}