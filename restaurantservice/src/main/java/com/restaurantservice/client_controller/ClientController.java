package com.restaurantservice.client_controller;

import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import com.restaurantservice.service.client.ClientRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class ClientController {
    @Autowired
    private ClientRestaurantService service;
    @GetMapping("/{name}")
    public ResponseEntity<RestaurantResponseDto> getRestByName(@PathVariable ("name")String name){
        return ResponseEntity.ok(service.getRestaurantByName(name));
    }
}
