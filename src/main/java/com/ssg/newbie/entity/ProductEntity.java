package com.ssg.newbie.entity;

import com.ssg.newbie.domain.OrderProduct;
import com.ssg.newbie.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    private int price;

    private int deliveryPrice;

    private int stockCount;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Product toDto() {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .deliveryPrice(this.deliveryPrice)
                .stockCount(this.stockCount)
                .build();
    }

    public OrderProduct toOrderProduct(int orderCount) {
        return OrderProduct.builder()
                .name(this.name)
                .price(this.price)
                .deliveryPrice(this.deliveryPrice)
                .orderCount(orderCount)
                .build();
    }

    public void reduceStock(int orderCount) {
        if (stockCount < orderCount) {
            throw new RuntimeException("재고가 부족하여 주문할 수 없습니다.");
        }
        this.stockCount -= orderCount;
    }
}
