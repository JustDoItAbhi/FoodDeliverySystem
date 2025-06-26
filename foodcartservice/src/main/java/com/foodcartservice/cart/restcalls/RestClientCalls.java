package com.foodcartservice.cart.restcalls;

import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientCalls {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public CartResponseDto responseEntity(String name){
        RestTemplate template=restTemplateBuilder.build();
        String url="http://localhost:8000/RestaurantCart/getCartAPI/"+name;
        ResponseEntity<CartResponseDto>response=template.getForEntity(url, CartResponseDto.class);
        if(response==null){
            throw new RuntimeException(" url not found ");
        }
        CartResponseDto dto=response.getBody();
        return dto;

    }
}
