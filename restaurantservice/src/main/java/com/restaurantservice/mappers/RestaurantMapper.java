package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.*;
import com.restaurantservice.entity.Addresses;
import com.restaurantservice.entity.NonVegMenu;
import com.restaurantservice.entity.Restaurants;
import com.restaurantservice.entity.VegMenu;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {
    public static RestaurantResponseDto forCreatingRest(Restaurants restaurants){
        RestaurantResponseDto dto=new RestaurantResponseDto();
        dto.setRestaurantId(restaurants.getId());
        dto.setRestaurantName(restaurants.getRestaurantName());

        List<AddressesResponseDto>list=new ArrayList<>();
        for(Addresses addresses: restaurants.getAddress()) {
            AddressesResponseDto addressesResponseDto=new AddressesResponseDto();
            addressesResponseDto.setAddressId(addresses.getId());
            addressesResponseDto.setBuildingNumber(addresses.getBuildingNumber());
            addressesResponseDto.setStreet(addresses.getStreet());
            addressesResponseDto.setCity(addresses.getCity());
            addressesResponseDto.setPinCode(addresses.getPinCode());
            addressesResponseDto.setCountry(addresses.getCountry());
            list.add(addressesResponseDto);
        }
        dto.setAddress(list);
        return dto;
    }
    public static RestaurantResponseDto fromRestaurantEntity(Restaurants restaurants){
        RestaurantResponseDto responseDto=forCreatingRest(restaurants);
        responseDto.setMenu(MenuMappers.fullMenuMapper(restaurants.getMenu()));
        responseDto.getMenu().setNonVegMenusResponse(NonVegMapper.fromNonVegMenuEntity(restaurants.getMenu().getNonVegMenus()));
        responseDto.getMenu().setVegMenusResponse(VegMenuMapper.fromVegMenuEntity(restaurants.getMenu().getVegMenus()));
        responseDto.getMenu().setPizzaResponseDtoList (PizzaMapper.fromPizzaEntity(restaurants.getMenu().getPizzaList()));
        return responseDto;
    }
}
