package com.ssg.newbie.domain.user;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.domain.discount.DeliveryDiscountPolicy;
import com.ssg.newbie.domain.discount.DiscountPolicy;
import com.ssg.newbie.domain.discount.ProductDiscountPolicy;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SmileClubUser implements User {

    private String name;

    @Override
    public UserClubType getClubType() {
        return UserClubType.SMILE_CLUB;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<DiscountPolicy> getDiscountPolicies() {
        return List.of(
                new ProductDiscountPolicy(),
                new DeliveryDiscountPolicy()
        );
    }
}
