package com.foodcartservice.cart.controller;

import com.foodcartservice.cart.responsedtos.RestaurantResponseDto;
import com.foodcartservice.cart.responsedtos.transf.OrderResponseDto;
import com.foodcartservice.cart.service.FoodCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class FoodCartController {
@Autowired
    private FoodCartService service;
    @GetMapping("/{name}")
    public ResponseEntity<RestaurantResponseDto> getRestByName(@PathVariable("name")String name){
        return ResponseEntity.ok(service.getRestaurant(name));
    }
    @GetMapping("/order/{name}")
    public ResponseEntity<OrderResponseDto> addToOrder(@PathVariable("name")String name){
        return ResponseEntity.ok(service.addOrder(name));
    }
}
