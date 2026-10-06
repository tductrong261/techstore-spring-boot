package com.techstore.service.impl;

import com.techstore.dto.request.CategoryRequest;
import com.techstore.dto.response.CategoryResponse;
import com.techstore.entity.Category;
import com.techstore.exception.AppException;
import com.techstore.exception.ErrorCode;
import com.techstore.mapper.CategoryMapper;
import com.techstore.repository.CategoryRepository;
import com.techstore.service.CategoryService;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(
            CategoryRequest request
    ) {

        validateDuplicateCategoryName(
                request.getName()
        );

        Category category =
                categoryMapper.toEntity(request);

        Category savedCategory =
                categoryRepository.save(category);

        return categoryMapper.toResponse(
                savedCategory
        );
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        var categories =
                categoryRepository.findAll();

        return categoryMapper.toResponseList(
                categories
        );
    }

    @Override
    public CategoryResponse getCategoryById(
            Long id
    ) {

        Category category =
                findCategoryById(id);

        return categoryMapper.toResponse(
                category
        );
    }

    @Override
    public CategoryResponse updateCategory(
            Long id,
            CategoryRequest request
    ) {

        Category category =
                findCategoryById(id);

        validateDuplicateCategoryNameForUpdate(
                request.getName(),
                id
        );

        categoryMapper.updateEntity(
                request,
                category
        );

        Category updatedCategory =
                categoryRepository.save(category);

        return categoryMapper.toResponse(
                updatedCategory
        );
    }

    @Override
    public void deleteCategory(Long id) {

        Category category =
                findCategoryById(id);

        categoryRepository.delete(category);
    }

    private Category findCategoryById(
            Long id
    ) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new AppException(
                                ErrorCode.CATEGORY_NOT_FOUND
                        )
                );
    }

    private void validateDuplicateCategoryName(
            String name
    ) {

        if (categoryRepository.existsByName(name)) {

            throw new AppException(
                    ErrorCode.CATEGORY_NAME_EXISTED
            );
        }
    }

    private void validateDuplicateCategoryNameForUpdate(
            String name,
            Long categoryId
    ) {

        if (categoryRepository
                .existsByNameAndIdNot(
                        name,
                        categoryId
                )) {

            throw new AppException(
                    ErrorCode.CATEGORY_NAME_EXISTED
            );
        }
    }
}