package com.restaurantservice.mappers;

import com.restaurantservice.cart.cartdtos.CartResponseDto;
import com.restaurantservice.cart.cartdtos.Items;
import com.restaurantservice.entity.cart.Cart;
import com.restaurantservice.entity.cart.Item;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public static CartResponseDto fromCartEntity(Cart cart){
        CartResponseDto dto=new CartResponseDto();
        dto.setCartId(cart.getId());
//        dto.setCreatetAt(cart.getCreatedAt());
        dto.setRestaurantId(cart.getRestaurantId());
        dto.setRestName(cart.getRestName());
        dto.setTotalPrice(cart.getTotalPrice());
        dto.setTotalQuantity(cart.getTotalQuantity());
        List<Items>cartItems=new ArrayList<>();
        for(Item item: cart.getItemsList()){
            Items items=new Items();
            items.setId(cart.getId());
            items.setFoodName(cart.getRestName());
            items.setQuantity(cart.getTotalQuantity());
            cartItems.add(items);
        }
        dto.setItemsList(cartItems);
        return dto;
    }
}
