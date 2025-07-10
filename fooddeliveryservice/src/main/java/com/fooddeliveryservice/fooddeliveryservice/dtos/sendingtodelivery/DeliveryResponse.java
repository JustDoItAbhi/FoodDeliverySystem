package com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery;

import com.fooddeliveryservice.fooddeliveryservice.entity.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class DeliveryResponse {
    private String email;
private DeliveryStatus deliveryStatus;
private LocalTime currentTime;
}
