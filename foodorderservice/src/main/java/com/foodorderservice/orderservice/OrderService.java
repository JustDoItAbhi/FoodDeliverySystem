package com.foodorderservice.orderservice;

import com.foodorderservice.dtos.CustomerResponseDto;
import com.foodorderservice.orderdtos.OrderResponseDto;

public interface OrderService {
    OrderResponseDto confirmOrder(String name);
    CustomerResponseDto getCustomerByEmail(String email);
}
