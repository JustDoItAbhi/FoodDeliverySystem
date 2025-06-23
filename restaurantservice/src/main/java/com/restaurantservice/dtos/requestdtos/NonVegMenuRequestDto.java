package com.restaurantservice.dtos.requestdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonVegMenuRequestDto {
    private String foodItem;
    private double quantity;
    private double price;
}
