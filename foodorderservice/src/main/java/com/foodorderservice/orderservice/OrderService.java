package com.foodorderservice.orderservice;

import com.foodorderservice.orderdtos.OrderResponseDto;

public interface OrderService {
    OrderResponseDto confirmOrder(String name);
}
