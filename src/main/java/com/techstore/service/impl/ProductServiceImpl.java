package com.techstore.service.impl;

import com.techstore.dto.request.ProductRequest;
import com.techstore.dto.response.ProductResponse;
import com.techstore.entity.Category;
import com.techstore.entity.Product;
import com.techstore.exception.DuplicateResourceException;
import com.techstore.exception.ResourceNotFoundException;
import com.techstore.mapper.ProductMapper;
import com.techstore.repository.CategoryRepository;
import com.techstore.repository.ProductRepository;
import com.techstore.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException(
                    "SKU already exists: " + request.getSku()
            );
        }

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: "
                                        + request.getCategoryId()
                        )
                );
        Product product = productMapper.toEntity(request);
        product.setCategory(category);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productMapper.updateEntity(request, product);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
