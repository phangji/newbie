package com.ssg.newbie.service;

import com.ssg.newbie.domain.Product;
import com.ssg.newbie.domain.ProductRequest;
import com.ssg.newbie.entity.ProductEntity;
import com.ssg.newbie.exception.ResourceNotFoundException;
import com.ssg.newbie.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> getProducts() {
        return productRepository.findAll().stream().map(ProductEntity::toDto)
                .collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        return getEntity(id).toDto();
    }

    public ProductEntity getEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product를 찾을 수 없습니다. productId = " + id));
    }

    public Long createProduct(ProductRequest request) {
        ProductEntity entity = new ProductEntity();
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setDeliveryPrice(request.getDeliveryPrice());
        entity.setStockCount(request.getStockCount());
        return productRepository.save(entity).getProductId();
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {
        ProductEntity entity = getEntity(id);
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setDeliveryPrice(request.getDeliveryPrice());
        entity.setStockCount(request.getStockCount());
        return entity.toDto();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
