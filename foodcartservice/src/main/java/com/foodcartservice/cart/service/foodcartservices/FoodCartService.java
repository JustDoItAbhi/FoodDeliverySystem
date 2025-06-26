package com.foodcartservice.cart.service.foodcartservices;

import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;

public interface FoodCartService {
    CartResponseDto getCart(String name);
}
