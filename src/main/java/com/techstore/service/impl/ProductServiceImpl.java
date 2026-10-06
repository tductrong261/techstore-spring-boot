package com.techstore.service.impl;

import com.techstore.dto.request.ProductRequest;
import com.techstore.dto.response.ProductResponse;
import com.techstore.entity.Category;
import com.techstore.entity.Product;
import com.techstore.exception.AppException;
import com.techstore.exception.ErrorCode;
import com.techstore.mapper.ProductMapper;
import com.techstore.repository.CategoryRepository;
import com.techstore.repository.ProductRepository;
import com.techstore.service.ProductService;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = findProductById(id);

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        validateDuplicateSku(request.getSku());

        Category category = findCategoryById(request.getCategoryId());

        Product product = productMapper.toEntity(request);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(
            Long id,
            ProductRequest request
    ) {

        Product product = findProductById(id);

        validateDuplicateSkuForUpdate(
                request.getSku(),
                id
        );

        Category category =
                findCategoryById(request.getCategoryId());

        productMapper.updateEntity(
                request,
                product
        );

        product.setCategory(category);

        Product updatedProduct =
                productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = findProductById(id);

        productRepository.delete(product);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new AppException(
                                ErrorCode.PRODUCT_NOT_FOUND
                        )
                );
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new AppException(
                                ErrorCode.CATEGORY_NOT_FOUND
                        )
                );
    }

    private void validateDuplicateSku(String sku) {

        if (productRepository.existsBySku(sku)) {
            throw new AppException(
                    ErrorCode.PRODUCT_SKU_EXISTED
            );
        }
    }

    private void validateDuplicateSkuForUpdate(
            String sku,
            Long productId
    ) {

        if (productRepository.existsBySkuAndIdNot(
                sku,
                productId
        )) {
            throw new AppException(
                    ErrorCode.PRODUCT_SKU_EXISTED
            );
        }
    }
}