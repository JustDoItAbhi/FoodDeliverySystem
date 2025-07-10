package com.foodpaymentservice.service.orderservice;

import com.foodpaymentservice.dtos.orderdtos.CustomerResponseDto;
import com.foodpaymentservice.dtos.orderdtos.OrderResponseDto;
import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;
import com.foodpaymentservice.sendingtodelivery.DeliveryDTO;

public interface OrderService {
    PaymentResponseDTO getCustomerAndOrder(String email, String restaurantName);
    DeliveryDTO createDelivery(String email);
}
