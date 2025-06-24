package com.restaurantservice.dtos.requestdtos.menuRequest;

import com.restaurantservice.entity.TypeOfSpicy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VegMenuRequetDto {
    private String foodItem;
    private double quantity;
    private double price;
    private TypeOfSpicy spicy;
}
