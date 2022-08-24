package com.ssg.newbie.domain.discount;

import com.ssg.newbie.domain.OrderProduct;

/**
 * 상품 합계 금액이 15000원 이상일 때, 배송비 무료
 */
public class DeliveryDiscountPolicy implements DiscountPolicy {

    private static final int FREE_DELIVERY_TOTAL_PRICE = 15000;

    @Override
    public DiscountResult calculateDiscount(OrderProduct product) {
        if (isFreeDelivery(product)) {
            return new DiscountResult(product.getPrice(), 0);
        }
        return new DiscountResult(product.getPrice(), product.getDeliveryPrice());
    }

    private boolean isFreeDelivery(OrderProduct product) {
        return product.getPrice() * product.getOrderCount() >= FREE_DELIVERY_TOTAL_PRICE;
    }
}
