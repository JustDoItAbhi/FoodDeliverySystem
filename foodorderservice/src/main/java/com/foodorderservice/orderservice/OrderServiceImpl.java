package com.foodorderservice.orderservice;


import com.foodorderservice.apicallsfromcustomer.HttpCallsFromServices;
import com.foodorderservice.dtos.CustomerResponseDto;
import com.foodorderservice.orderdtos.OrderResponseDto;
import com.foodorderservice.orderdtos.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisTemplate<String,OrderResponseDto>redisTemplate;
    @Autowired
    private HttpCallsFromServices callsFromServices;

    @Override
    public OrderResponseDto confirmOrder(String name) {
        OrderResponseDto dto=redisTemplate.opsForValue().get(name);
        dto.setOrderStatus(OrderStatus.ORDER_CONFIRM);
        return dto;
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) {
        CustomerResponseDto dto=callsFromServices.getCustomer(email);
        if(dto==null|| !dto.getEmail().equals(email)){
            throw new RuntimeException("PLEASE SIGNUP AND ADD YOUR DELIVERY ADDRESS");
        }
        return dto;
    }

}
