package com.ssg.newbie.repository;

import com.ssg.newbie.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select p from ProductEntity p order by p.price asc")
    List<ProductEntity> findOrderByPriceDesc();
}
