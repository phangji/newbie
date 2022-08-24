package com.ssg.newbie.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class Product {
    private String name;
    private int price;
    private int deliveryPrice;
    private int stockCount;
}
