package com.techstore.controller;

import com.techstore.entity.Product;
import com.techstore.service.CategoryService;
import com.techstore.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {
    ProductService productService;
    CategoryService categoryService;

    // List all products
    @GetMapping
    public String listProducts(Model model) {
        // Implementation here
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    // Show form to create a new product
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        // Implementation here
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/form";
    }

    // Save a new product
    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        // Implementation here
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Show form to edit an existing product
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/form";
    }

    // Update an existing product
    @PostMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product) {
        // Implementation here
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Delete a product
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
