package com.foodorderservice.orderdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private String email;
    private long restaurantId;
    private String restName;
    private long cartId;
    private CartStatus cartStatus;
    private double totalQuantity;
    private double totalPrice;
    private OrderStatus orderStatus;
}
