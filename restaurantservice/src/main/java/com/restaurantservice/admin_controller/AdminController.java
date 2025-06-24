package com.restaurantservice.admin_controller;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.RestaurantRequestDto;
import com.restaurantservice.dtos.responsedtos.*;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.service.admin.AdminRestaurantsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRestaurantsServices services;
    @PostMapping("/create")
    public ResponseEntity<RestaurantResponseDto> createRest(@RequestBody RestaurantRequestDto dto){
        return ResponseEntity.ok(services.createRestaurant(dto));
    }
    @PostMapping("/createNonVegMenu")
    public ResponseEntity<MenuResponseDto> createNonVegMenu(@RequestBody MenuRequestDto dto){
        return ResponseEntity.ok(services.createNonVegMenu(dto));
    }
    @PostMapping("/createVegMenu")
    public ResponseEntity<MenuResponseDto> createVegMenu(@RequestBody MenuRequestDto dto){
        return ResponseEntity.ok(services.createVegMenu(dto));
    }
    @PostMapping("/createPizza")
    public ResponseEntity<MenuResponseDto> createPizzaMenu(@RequestBody MenuRequestDto dto){
        return ResponseEntity.ok(services.addPizza(dto));
    }
    @GetMapping("/{name}")
    public ResponseEntity<RestaurantResponseDto> getRestByName(@PathVariable ("name")String name){
        return ResponseEntity.ok(services.getRestaurantByName(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MenuResponseDto> getMenuByName(@PathVariable ("id")long id){
        return ResponseEntity.ok(services.getById(id));
    }



}
