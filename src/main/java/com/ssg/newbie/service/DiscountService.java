package com.ssg.newbie.service;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.domain.OrderProduct;
import com.ssg.newbie.domain.discount.DiscountResult;
import com.ssg.newbie.domain.user.SmileClubUser;
import com.ssg.newbie.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public DiscountResult calculateDiscount(User user, OrderProduct product) {
        return user.getDiscountPolicies().stream().map(discountPolicy -> discountPolicy.calculateDiscount(product))
                .reduce(new DiscountResult(product.getPrice(), product.getDeliveryPrice()), this::reduceResult);
    }

    private DiscountResult reduceResult(DiscountResult a, DiscountResult b) {
        return new DiscountResult(
                Math.min(a.getPrice(), b.getPrice()),
                Math.min(b.getDeliveryPrice(), b.getDeliveryPrice())
        );
    }
}
/**
 * 스마일 회원
 * (1) DiscountResult price = 900 , delivery = 2500 (a)
 * (2) DiscountResult price = 1000, delivery = 0 (b)
 * -> DiscountResult pirce = 900, delivery = 0
 */