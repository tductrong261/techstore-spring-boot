package com.techstore.controller;

import com.techstore.dto.request.CategoryRequest;
import com.techstore.dto.response.ApiResponse;
import com.techstore.dto.response.CategoryResponse;
import com.techstore.service.CategoryService;
import jakarta.validation.Valid;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request
    ) {

        var result =
                categoryService.createCategory(request);

        return ApiResponse
                .<CategoryResponse>builder()
                .data(result)
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>>
    getAllCategories() {

        var result =
                categoryService.getAllCategories();

        return ApiResponse
                .<List<CategoryResponse>>builder()
                .data(result)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(
            @PathVariable Long id
    ) {

        var result =
                categoryService.getCategoryById(id);

        return ApiResponse
                .<CategoryResponse>builder()
                .data(result)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request
    ) {

        var result =
                categoryService.updateCategory(
                        id,
                        request
                );

        return ApiResponse
                .<CategoryResponse>builder()
                .data(result)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(
            @PathVariable Long id
    ) {

        categoryService.deleteCategory(id);

        return ApiResponse.<Void>builder()
                .build();
    }
}