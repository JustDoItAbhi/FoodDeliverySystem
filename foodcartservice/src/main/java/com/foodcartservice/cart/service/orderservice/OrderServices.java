package com.foodcartservice.cart.service.orderservice;

import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;
import com.foodcartservice.cart.dtos.orderdtos.OrderResponseDto;

public interface OrderServices {
    OrderResponseDto createOrder(String name);
    OrderResponseDto getOrderByName(String name);
}
