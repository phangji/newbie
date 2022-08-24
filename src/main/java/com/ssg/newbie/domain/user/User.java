package com.ssg.newbie.domain.user;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.domain.discount.DiscountPolicy;

import java.util.List;

public interface User {
    UserClubType getClubType();

    String getName();

    List<DiscountPolicy> getDiscountPolicies();
}
