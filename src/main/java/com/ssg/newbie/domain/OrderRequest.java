package com.ssg.newbie.domain;

import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private Long productId;
    private int productCount;
}
