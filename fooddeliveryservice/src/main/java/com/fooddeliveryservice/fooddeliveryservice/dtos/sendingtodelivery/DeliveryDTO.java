package com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDTO {
    private long restaurantId;
    private String restName;
    private long cartId;
    private double totalQuantity;
    private double totalPrice;
    private String customerName;
    private String email;
    private AddressResponseDTO address;
}
