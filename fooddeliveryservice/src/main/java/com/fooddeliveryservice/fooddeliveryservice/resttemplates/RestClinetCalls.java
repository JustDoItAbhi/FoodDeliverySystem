package com.fooddeliveryservice.fooddeliveryservice.resttemplates;

import com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery.DeliveryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClinetCalls {
@Autowired
    public RestTemplateBuilder restTemplateBuilder;
    @Cacheable(value = "DeliveryDTO",key = "#email")
public DeliveryDTO responseEntity(String email){
    RestTemplate template=new RestTemplate();
    String url="http://localhost:5000/payment/createDelivery/"+email;
    ResponseEntity<DeliveryDTO>response=template.getForEntity(url, DeliveryDTO.class);
    if(response==null){
        throw new RuntimeException("UNABLE TO CALL PAYMENT SERVICE ");
    }
    return response.getBody();
}
}
