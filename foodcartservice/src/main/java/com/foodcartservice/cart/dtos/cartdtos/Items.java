package com.foodcartservice.cart.dtos.cartdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Items {
    private long id;
    private String foodName;
    private double quantity;

    public Items() {
    }
}
