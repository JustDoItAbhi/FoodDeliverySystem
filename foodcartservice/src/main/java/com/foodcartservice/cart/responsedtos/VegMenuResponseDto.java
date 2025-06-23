package com.foodcartservice.cart.responsedtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VegMenuResponseDto {
    private long vegMenuId;
    private String foodItem;
    private double quantity;
    private double price;
}
