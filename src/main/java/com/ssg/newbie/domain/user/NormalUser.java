package com.ssg.newbie.domain.user;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.domain.discount.DiscountPolicy;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class NormalUser implements User {
    private String name;

    @Override
    public UserClubType getClubType() {
        return UserClubType.NORMAL;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<DiscountPolicy> getDiscountPolicies() {
        return Collections.emptyList();
    }
}
