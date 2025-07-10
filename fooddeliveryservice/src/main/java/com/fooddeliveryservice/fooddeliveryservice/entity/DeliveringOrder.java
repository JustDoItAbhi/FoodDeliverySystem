package com.fooddeliveryservice.fooddeliveryservice.entity;

import com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery.AddressResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeliveringOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long restaurantId;
    private String restName;
    private long cartId;
    private double totalQuantity;
    private double totalPrice;
    private String customerName;
    private String email;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
