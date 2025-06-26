package com.foodcartservice.cart.dtos.cartrequestdto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ItemsRequestDto {
    private long id;
    private String foodName;
    private double quantity;

}
