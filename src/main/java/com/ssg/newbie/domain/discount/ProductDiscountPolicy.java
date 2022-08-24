package com.ssg.newbie.domain.discount;

import com.ssg.newbie.domain.OrderProduct;

/**
 * 상품 가격의 10%를 할인해주는 정책
 */
public class ProductDiscountPolicy implements DiscountPolicy {
    @Override
    public DiscountResult calculateDiscount(OrderProduct product) {
        return new DiscountResult(Math.round(product.getPrice() * 0.9f), product.getDeliveryPrice());
    }
}
