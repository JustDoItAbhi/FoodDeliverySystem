package com.fooddeliveryservice.fooddeliveryservice.controller;

import com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery.DeliveryResponse;
import com.fooddeliveryservice.fooddeliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodDelivery")
public class DeliveryController {
@Autowired
    private DeliveryService deliveryService;
@GetMapping("/getDeliveryStatus/{email}")
    public ResponseEntity<DeliveryResponse> getResponse(@PathVariable("email")String  email){
    return ResponseEntity.ok(deliveryService.startCooking(email));
}
}
