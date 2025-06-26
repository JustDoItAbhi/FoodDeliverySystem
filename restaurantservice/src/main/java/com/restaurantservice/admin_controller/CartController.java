package com.restaurantservice.admin_controller;

import com.restaurantservice.cart.cartdtos.CartResponseDto;
import com.restaurantservice.cart.cartdtos.cartrequestdto.CreateCartRequestDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import com.restaurantservice.service.cartservice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestaurantCart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/createCart")
    public ResponseEntity<CartResponseDto>createCart(@RequestBody CreateCartRequestDto dto){
        return ResponseEntity.ok(cartService.createCart(dto)); // added to cache
    }
    @GetMapping("/getCartAPI/{name}")
    public ResponseEntity<CartResponseDto>getFinalCart(@PathVariable("name")String name){
        return ResponseEntity.ok(cartService.getFinalApi(name));
    }
    @GetMapping("/getAllCarts")
    public ResponseEntity<List<CartResponseDto>> getAllCart(){
        return ResponseEntity.ok(cartService.getAll());
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable ("id")Long Id){
        String mesage =cartService.deleteCartById(Id);
        return ResponseEntity.ok(mesage);
    }





//    @GetMapping("/{name}")
//    public ResponseEntity<CartResponseDto> getRestByName(@PathVariable ("name")String name){
//        return ResponseEntity.ok(cartService.getCartByRestaurant(name));
//    }
}
