package com.restaurantservice.dtos.responsedtos.menuResponse;

import com.restaurantservice.entity.PizzaSize;
import com.restaurantservice.entity.TypeOfSpicy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaResponseDto {
    private long id;
    private String pizzaName;
    private PizzaSize pizzaSize;
    private double quantity;
    private double price;
    private TypeOfSpicy spicy;

}
