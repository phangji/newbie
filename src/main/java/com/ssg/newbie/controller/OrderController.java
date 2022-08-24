package com.ssg.newbie.controller;

import com.ssg.newbie.domain.Order;
import com.ssg.newbie.domain.OrderRequest;
import com.ssg.newbie.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/api/external/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/api/external/orders/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/api/external/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
}
