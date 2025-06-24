package com.restaurantservice.dtos.responsedtos.menuResponse;

import com.restaurantservice.entity.TypeOfSpicy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VegMenuResponseDto {
    private long vegMenuId;
    private String foodItem;
    private double quantity;
    private double price;
    private TypeOfSpicy spicy;
}
