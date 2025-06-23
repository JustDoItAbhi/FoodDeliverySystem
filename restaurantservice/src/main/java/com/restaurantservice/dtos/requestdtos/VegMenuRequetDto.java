package com.restaurantservice.dtos.requestdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VegMenuRequetDto {
    private String foodItem;
    private double quantity;
    private double price;
}
