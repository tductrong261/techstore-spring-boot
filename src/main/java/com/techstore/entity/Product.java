package com.techstore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 200)
  String name;

  @Column(nullable = false, unique = true, length = 50)
  String sku;

  @Column(columnDefinition = "TEXT")
  String description;

  @Column(nullable = false, precision = 15, scale = 2)
  BigDecimal price;

  @Column(nullable = false)
  Integer stock;

  @Column(length = 500)
  String imageUrl;

  @Column(nullable = false)
  Boolean active = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
          name = "category_id",
          nullable = false
  )
  Category category;
}
