package com.techstore.mapper;

import com.techstore.dto.request.ProductRequest;
import com.techstore.dto.response.ProductResponse;
import com.techstore.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "category", ignore = true)
  Product toEntity(ProductRequest request);

  @Mapping(source = "category.id", target = "categoryId")
  @Mapping(source = "category.name", target = "categoryName")
  ProductResponse toResponse(Product product);

  List<ProductResponse> toResponseList(List<Product> products);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "category", ignore = true)
  void updateEntity(ProductRequest request, @MappingTarget Product product);
}
