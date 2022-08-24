package com.ssg.newbie.domain;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private int price;
    private int deliveryPrice;
    private int stockCount;
}
