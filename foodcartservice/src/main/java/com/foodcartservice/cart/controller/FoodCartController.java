package com.foodcartservice.cart.controller;

import com.foodcartservice.cart.dtos.cartdtos.CartResponseDto;
import com.foodcartservice.cart.dtos.orderdtos.OrderResponseDto;
import com.foodcartservice.cart.service.foodcartservices.FoodCartService;
import com.foodcartservice.cart.service.orderservice.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class FoodCartController {
@Autowired
private FoodCartService service;
@Autowired
private OrderServices orderServices;
    @GetMapping("/getCart/{name}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable("name")String name){
        return ResponseEntity.ok(service.getCart(name));
    }
    @GetMapping("/addOrder/{name}")
    public ResponseEntity<OrderResponseDto> addOrder(@PathVariable("name")String name){
        return ResponseEntity.ok(orderServices.createOrder(name));
    }
    @GetMapping("/checkOrder/{name}")
    public ResponseEntity<OrderResponseDto> checkOrder(@PathVariable("name")String name){
        return ResponseEntity.ok(orderServices.getOrderByName(name));
    }

}
