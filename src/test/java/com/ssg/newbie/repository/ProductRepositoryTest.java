package com.ssg.newbie.repository;

import com.ssg.newbie.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void test_findOrderByPriceDesc() {
        // given
        saveProduct("사과", 1000, 0, 10);
        saveProduct("배", 1500, 0, 10);

        // when
        List<ProductEntity> result = productRepository.findOrderByPriceDesc();

        // then
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getName(), "사과");
        Assertions.assertEquals(result.get(1).getName(), "배");
    }

    void saveProduct(String name, int price, int deliveryPrice, int stockCount) {
        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        product.setDeliveryPrice(deliveryPrice);
        product.setStockCount(stockCount);
        productRepository.save(product);
    }
}
