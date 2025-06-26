package com.foodpaymentservice.service.orderservice;

import com.foodpaymentservice.dtos.orderdtos.CustomerResponseDto;
import com.foodpaymentservice.dtos.orderdtos.OrderResponseDto;
import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;
import com.foodpaymentservice.httpscalls.HttpCallsFromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
@Autowired
private HttpCallsFromService callsFromService;
@Autowired
private RedisTemplate<String,PaymentResponseDTO>redisTemplate;

    @Override
    public PaymentResponseDTO getCustomerAndOrder(String email, String restaurantName) {
        CustomerResponseDto customerResponseDto= callsFromService.fetchCustomer(email);
        PaymentResponseDTO responseDTO=new PaymentResponseDTO();
        responseDTO.setId(customerResponseDto.getId());
        responseDTO.setCustomerName(customerResponseDto.getCustomerName());
        responseDTO.setEmail(customerResponseDto.getEmail());
        responseDTO.setAddress(customerResponseDto.getAddress());

        OrderResponseDto orderResponseDto= callsFromService.fetchOrder(restaurantName);
        responseDTO.setOrderStatus(orderResponseDto.getOrderStatus());
        responseDTO.setRestaurantId(orderResponseDto.getRestaurantId());
        responseDTO.setRestName(orderResponseDto.getRestName());
        responseDTO.setTotalQuantity(orderResponseDto.getTotalQuantity());
        responseDTO.setTotalPrice(orderResponseDto.getTotalPrice());
        redisTemplate.opsForValue().set(email,responseDTO);

        return responseDTO;
    }
}
