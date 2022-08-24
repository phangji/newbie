package com.ssg.newbie.discount;

import com.ssg.newbie.domain.OrderProduct;
import com.ssg.newbie.domain.discount.DeliveryDiscountPolicy;
import com.ssg.newbie.domain.discount.DiscountResult;
import com.ssg.newbie.domain.discount.ProductDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductDiscountPolicyTest {

    @Test
    @DisplayName("10% 할인 적용")
    public void test_productDiscount() {
        ProductDiscountPolicy policy = new ProductDiscountPolicy();

        DiscountResult result = policy.calculateDiscount(OrderProduct.builder()
                .name("사과")
                .price(1000)
                .deliveryPrice(2500)
                .orderCount(20)
                .build());

        Assertions.assertEquals(result.getPrice(), 900);
        Assertions.assertEquals(result.getDeliveryPrice(), 2500);
    }
}
