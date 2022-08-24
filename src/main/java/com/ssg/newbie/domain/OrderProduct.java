package com.ssg.newbie.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProduct {
    private String name;
    private int price;
    private int deliveryPrice;
    private int orderCount;
}