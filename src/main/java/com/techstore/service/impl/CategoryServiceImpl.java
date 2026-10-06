package com.techstore.service.impl;

import com.techstore.entity.Category;
import com.techstore.repository.CategoryRepository;
import com.techstore.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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
