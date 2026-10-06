package com.techstore.service.impl;

import com.techstore.entity.Category;
import com.techstore.repository.CategoryRepository;
import com.techstore.service.CategoryService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

  CategoryRepository categoryRepository;

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }
}
