package com.ssg.newbie.entity;

import com.ssg.newbie.domain.Order;
import com.ssg.newbie.domain.discount.DiscountResult;
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
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int productCount;

    private int orderPrice;

    private int finalPrice;

    private int deliveryPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void applyDiscountResult(DiscountResult result) {
        this.finalPrice = result.getPrice() * this.productCount;
        this.deliveryPrice = result.getDeliveryPrice();
    }

    public Order toDto() {
        return Order.builder()
                .userName(user.getName())
                .productName(product.getName())
                .productCount(this.productCount)
                .orderPrice(this.orderPrice)
                .finalPrice(this.finalPrice)
                .deliveryPrice(this.deliveryPrice)
                .build();
    }
}
