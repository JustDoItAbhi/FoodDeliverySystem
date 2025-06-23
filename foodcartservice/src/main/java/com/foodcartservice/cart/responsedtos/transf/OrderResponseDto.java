package com.foodcartservice.cart.responsedtos.transf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;
    private FoodItems foodItems;
    private double totalPrice;
}
