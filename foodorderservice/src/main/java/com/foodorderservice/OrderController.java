package com.foodorderservice;

import com.foodorderservice.orderdtos.OrderResponseDto;
import com.foodorderservice.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
@Autowired
    private OrderService orderService;
@GetMapping("/order/{name}")
    public ResponseEntity<OrderResponseDto> confirmOrder(@PathVariable("name")String name){

    return ResponseEntity.ok(orderService.confirmOrder(name));
}
}
