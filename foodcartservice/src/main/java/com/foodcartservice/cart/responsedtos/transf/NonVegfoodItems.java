package com.foodcartservice.cart.responsedtos.transf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonVegfoodItems {
    private String foodItem;
    private double quantity;
    private double price;
}
