package com.restaurantservice.cart.cartdtos.cartrequestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    private long id;
    private String foodName;
    private double quantity;
}
