package com.ssg.newbie.service;

import com.ssg.newbie.domain.Order;
import com.ssg.newbie.domain.OrderRequest;
import com.ssg.newbie.domain.discount.DiscountResult;
import com.ssg.newbie.entity.OrderEntity;
import com.ssg.newbie.entity.ProductEntity;
import com.ssg.newbie.entity.UserEntity;
import com.ssg.newbie.exception.ResourceNotFoundException;
import com.ssg.newbie.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final UserService userService;

    private final DiscountService discountService;

    @Transactional
    public Long createOrder(OrderRequest orderRequest) {
        UserEntity user = userService.getEntity(orderRequest.getUserId());
        ProductEntity product = productService.getEntity(orderRequest.getProductId());
        product.reduceStock(orderRequest.getProductCount());

        DiscountResult result = discountService.calculateDiscount(user.toDto(), product.toOrderProduct(orderRequest.getProductCount()));
        OrderEntity entity = new OrderEntity();
        entity.setUser(user);
        entity.setProduct(product);
        entity.setOrderPrice(product.getPrice() * orderRequest.getProductCount());
        entity.setProductCount(orderRequest.getProductCount());
        entity.applyDiscountResult(result);
        return orderRepository.save(entity).getOrderId();
    }

    public OrderEntity getEntity(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("order를 찾을 수 없습니다. orderId = " + id));
    }

    public Order getOrder(Long id) {
        return getEntity(id).toDto();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll().stream().map(OrderEntity::toDto)
                .collect(Collectors.toList());
    }

}
