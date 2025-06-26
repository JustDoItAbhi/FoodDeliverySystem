package com.restaurantservice.service.cartservice;

import com.restaurantservice.cart.cartdtos.CartResponseDto;
import com.restaurantservice.cart.cartdtos.cartrequestdto.CreateCartRequestDto;

import java.util.List;

public interface CartService {
    CartResponseDto createCart(CreateCartRequestDto dto);
    CartResponseDto getFinalApi(String name);
    List<CartResponseDto>getAll();
    String deleteCartById(long id);
}
