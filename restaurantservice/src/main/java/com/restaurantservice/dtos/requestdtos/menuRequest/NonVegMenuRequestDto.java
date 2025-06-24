package com.restaurantservice.dtos.requestdtos.menuRequest;

import com.restaurantservice.entity.TypeOfSpicy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonVegMenuRequestDto {
    private String foodItem;
    private double quantity;
    private double price;
    private TypeOfSpicy spicy;
}
