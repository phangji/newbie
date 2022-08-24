package com.ssg.newbie.controller;

import com.ssg.newbie.domain.Product;
import com.ssg.newbie.domain.ProductRequest;
import com.ssg.newbie.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/internal/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/api/internal/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/api/internal/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/api/internal/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/api/internal/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
