package com.foodpaymentservice.dtos.orderdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {
    private long restaurantId;
    private String restName;
    private long cartId;
    private double totalQuantity;
    private double totalPrice;
    private OrderStatus orderStatus;
    private long id;
    private String customerName;
    private String email;
    private AddressResponseDTO address;
    private String paymentlink;
}

