package com.restaurantservice.cart.cartdtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class CartResponseDto {
    private long cartId;
    private long restaurantId;
    private String restName;
    private List<Items> itemsList;
    private double totalQuantity;
    private double totalPrice;

}
