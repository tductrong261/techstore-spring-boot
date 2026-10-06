package com.techstore.repository;

import com.techstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsBySku(String sku);

  boolean existsBySkuAndIdNot(String sku, Long id);

  boolean existsByCategory_Id(Long categoryId);
}
