package com.ssg.newbie.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private String userName;
    private String productName;
    private int productCount;
    private int orderPrice;
    private int finalPrice;
    private int deliveryPrice;
}
