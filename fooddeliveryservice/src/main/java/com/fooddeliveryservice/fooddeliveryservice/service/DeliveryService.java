package com.fooddeliveryservice.fooddeliveryservice.service;

import com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery.DeliveryResponse;
import com.fooddeliveryservice.fooddeliveryservice.entity.DeliveryStatus;

public interface DeliveryService {
   DeliveryResponse startCooking(String email);

}
