package com.restaurantservice.dtos.requestdtos.menuRequest;

import com.restaurantservice.entity.PizzaSize;
import com.restaurantservice.entity.TypeOfSpicy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaRequestDto {
    private String pizzaName;
    private PizzaSize pizzaSize;
    private double quantity;
    private double price;
    private TypeOfSpicy spicy;
}
