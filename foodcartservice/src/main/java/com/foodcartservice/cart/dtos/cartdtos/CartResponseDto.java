package com.foodcartservice.cart.dtos.cartdtos;

import com.foodcartservice.cart.dtos.orderdtos.CartStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponseDto {
    private long cartId;
    private CartStatus cartStatus;
    private long restaurantId;
    private String restName;
    private List<Items> itemsList;
    private double totalQuantity;
    private double totalPrice;

}
