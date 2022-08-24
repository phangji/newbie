package com.ssg.newbie.domain.discount;

import com.ssg.newbie.domain.OrderProduct;

public interface DiscountPolicy {
    DiscountResult calculateDiscount(OrderProduct product);
}
