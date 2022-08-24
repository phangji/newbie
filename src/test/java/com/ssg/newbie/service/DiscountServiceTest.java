package com.ssg.newbie.service;

import com.ssg.newbie.domain.OrderProduct;
import com.ssg.newbie.domain.discount.DiscountResult;
import com.ssg.newbie.domain.user.NormalUser;
import com.ssg.newbie.domain.user.SmileClubUser;
import com.ssg.newbie.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountServiceTest {

    private DiscountService discountService = new DiscountService();

    @Test
    @DisplayName("스마일 클럽 회원 할인 적용")
    public void test_smileClubUser() {
        User user = new SmileClubUser("상냥한 호랑이");

        DiscountResult result = discountService.calculateDiscount(user, freeDeliveryProduct());

        Assertions.assertEquals(result.getPrice(), 900);
        Assertions.assertEquals(result.getDeliveryPrice(), 0);
    }

    @Test
    @DisplayName("일반 클럽 회원 할인 없음")
    public void test_normalUser() {
        User user = new NormalUser("불친절한 토끼");

        DiscountResult result = discountService.calculateDiscount(user, freeDeliveryProduct());

        Assertions.assertEquals(result.getPrice(), 1000);
        Assertions.assertEquals(result.getDeliveryPrice(), 2500);
    }

    private OrderProduct freeDeliveryProduct() {
        return OrderProduct.builder()
                .name("고구마")
                .price(1000)
                .orderCount(20)
                .deliveryPrice(2500)
                .build();
    }
}
