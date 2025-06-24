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
    private AdminRestaurantsServices restServices;

    @PostMapping("/create")
    public ResponseEntity<RestaurantResponseDto> createRest(@RequestBody RestaurantRequestDto dto){
        return ResponseEntity.ok(restServices.createRestaurant(dto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<RestaurantResponseDto> getRestByName(@PathVariable ("name")String name){
        return ResponseEntity.ok(restServices.getRestaurantByName(name));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<MenuResponseDto> getMenuByName(@PathVariable ("id")long id){
        return ResponseEntity.ok(restServices.getById(id));
    }



}
