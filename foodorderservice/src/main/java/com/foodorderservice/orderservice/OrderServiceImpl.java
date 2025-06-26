package com.foodorderservice.orderservice;


import com.foodorderservice.orderdtos.OrderResponseDto;
import com.foodorderservice.orderdtos.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisTemplate<String,OrderResponseDto>redisTemplate;

    @Override
    public OrderResponseDto confirmOrder(String name) {
        OrderResponseDto dto=redisTemplate.opsForValue().get(name);
        dto.setOrderStatus(OrderStatus.ORDER_CONFIRM);
        return dto;
    }
}
