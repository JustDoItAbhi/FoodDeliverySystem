package com.foodcartservice.cart.responsedtos.transf;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodItems {
    private String message;
    private TypesOfSpicy spicy;
    private List<VegfoodItems> vegfoodItems;
    private List<NonVegfoodItems> nonVegfoodItems;
    private double quantity;
}
