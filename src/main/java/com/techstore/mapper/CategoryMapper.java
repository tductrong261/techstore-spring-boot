package com.techstore.mapper;

import com.techstore.dto.request.CategoryRequest;
import com.techstore.dto.response.CategoryResponse;
import com.techstore.entity.Category;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  @Mapping(target = "id", ignore = true)
  Category toEntity(CategoryRequest request);

  CategoryResponse toResponse(Category category);

  List<CategoryResponse> toResponseList(List<Category> categories);

  @Mapping(target = "id", ignore = true)
  void updateEntity(CategoryRequest request, @MappingTarget Category category);
}
