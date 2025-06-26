package com.restaurantservice.kafka;

import com.restaurantservice.cart.cartdtos.CartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FoodItemProducer {
    @Autowired
    private KafkaTemplate<String, CartResponseDto>kafkaTemplate;
    private static final String Topic = "rest-cartl";
    public void sendFoodItem(CartResponseDto dto){
        kafkaTemplate.send(Topic,dto);
    }
}
