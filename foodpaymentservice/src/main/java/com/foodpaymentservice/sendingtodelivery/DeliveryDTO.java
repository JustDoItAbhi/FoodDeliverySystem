package com.foodpaymentservice.sendingtodelivery;

import com.foodpaymentservice.dtos.orderdtos.AddressResponseDTO;
import com.foodpaymentservice.dtos.orderdtos.OrderStatus;
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
    private String message;
}
