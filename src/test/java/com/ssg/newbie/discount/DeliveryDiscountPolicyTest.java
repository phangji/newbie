package com.ssg.newbie.discount;

import com.ssg.newbie.domain.OrderProduct;
import com.ssg.newbie.domain.discount.DeliveryDiscountPolicy;
import com.ssg.newbie.domain.discount.DiscountResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeliveryDiscountPolicyTest {

    @Test
    @DisplayName("배송비가 충족된 경우")
    public void test_freeDelivery() {
        DeliveryDiscountPolicy policy = new DeliveryDiscountPolicy();

        DiscountResult result = policy.calculateDiscount(OrderProduct.builder()
                .name("사과")
                .price(1000)
                .deliveryPrice(2500)
                .orderCount(20)
                .build());

        Assertions.assertEquals(result.getPrice(), 1000);
        Assertions.assertEquals(result.getDeliveryPrice(), 0);
    }

    @Test
    @DisplayName("배송비가 불충분한 경우")
    public void test_notFreeDelivery() {
        DeliveryDiscountPolicy policy = new DeliveryDiscountPolicy();

        DiscountResult result = policy.calculateDiscount(OrderProduct.builder()
                .name("사과")
                .price(1000)
                .deliveryPrice(2500)
                .orderCount(10)
                .build());

        Assertions.assertEquals(result.getPrice(), 1000);
        Assertions.assertEquals(result.getDeliveryPrice(), 2500);
    }
}
