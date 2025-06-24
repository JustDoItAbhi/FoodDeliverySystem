package com.restaurantservice.admin_controller;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.service.admin.pizzamenuserv.PizzaMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createPizza")
public class PizzaController {
    @Autowired
    private PizzaMenuService services;

    @PostMapping("/createPizza")
    public ResponseEntity<MenuResponseDto> createPizzaMenu(@RequestBody MenuRequestDto dto){
        return ResponseEntity.ok(services.addPizza(dto));
    }
}
