package com.foodcartservice.cart.responsedtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponseDto {
    private long menuId;
    private String restaurantName;
    List<NonVegMenuResponseDto> nonVegMenusResponse;
    List<VegMenuResponseDto>vegMenusResponse;
}
