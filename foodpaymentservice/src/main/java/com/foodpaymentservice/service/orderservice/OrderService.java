package com.foodpaymentservice.service.orderservice;

import com.foodpaymentservice.dtos.orderdtos.CustomerResponseDto;
import com.foodpaymentservice.dtos.orderdtos.OrderResponseDto;
import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;

public interface OrderService {
    PaymentResponseDTO getCustomerAndOrder(String email, String restaurantName);
}
