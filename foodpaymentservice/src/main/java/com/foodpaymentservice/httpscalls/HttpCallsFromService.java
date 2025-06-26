package com.foodpaymentservice.httpscalls;

import com.foodpaymentservice.dtos.orderdtos.CustomerResponseDto;
import com.foodpaymentservice.dtos.orderdtos.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpCallsFromService {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    public CustomerResponseDto fetchCustomer(String email){
        String url="http://localhost:6000/order/checkCustomer/"+email;
        RestTemplate template=restTemplateBuilder.build();
        ResponseEntity<CustomerResponseDto>response=template.getForEntity(url,CustomerResponseDto.class);
        if(response==null){
            throw new RuntimeException("invalid api request by "+ email);
        }
      CustomerResponseDto dto=response.getBody();
        return dto;
    }
    public OrderResponseDto fetchOrder(String restaurantName){
        String url="http://localhost:6000/order/"+restaurantName;
        RestTemplate template=restTemplateBuilder.build();
        ResponseEntity<OrderResponseDto>response=template.getForEntity(url,OrderResponseDto.class);
        if(response==null){
            throw new RuntimeException("invalid api request by "+ restaurantName);
        }
        OrderResponseDto dto=response.getBody();
        return dto;
    }
}
