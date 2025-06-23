package com.restaurantservice.mappers;

import com.restaurantservice.dtos.responsedtos.*;
import com.restaurantservice.entity.Addresses;
import com.restaurantservice.entity.NonVegMenu;
import com.restaurantservice.entity.Restaurants;
import com.restaurantservice.entity.VegMenu;

import java.util.ArrayList;
import java.util.List;

public class ClinetMapper {
    public static RestaurantResponseDto getClinetRest(Restaurants restaurants){
        RestaurantResponseDto dto=RestaurantMapper.forCreatingRest(restaurants);
//        MenuResponseDto menuResponseDto=MenuMappers.fromNonVegMenuEntity(restaurants.getMenu());
//        menuResponseDto=MenuMappers.fromVegMenuEntity(restaurants.getMenu());
//        dto.setMenu(menuResponseDto);


//        List<AddressesResponseDto> list=new ArrayList<>();
//        for(Addresses addresses: restaurants.getAddress()) {
//            AddressesResponseDto addressesResponseDto=new AddressesResponseDto();
//            addressesResponseDto.setAddressId(addresses.getId());
//            addressesResponseDto.setBuildingNumber(addresses.getBuildingNumber());
//            addressesResponseDto.setStreet(addresses.getStreet());
//            addressesResponseDto.setCity(addresses.getCity());
//            addressesResponseDto.setPinCode(addresses.getPinCode());
//            addressesResponseDto.setCountry(addresses.getCountry());
//            list.add(addressesResponseDto);
//        }

//        MenuResponseDto menuResponseDto=new MenuResponseDto();
//        menuResponseDto.setMenuId(restaurants.getMenu().getId());
//        List<NonVegMenuResponseDto>nonVegMenuResponseDtos=new ArrayList<>();

//        for(NonVegMenu nonVegMenu:restaurants.getMenu().getNonVegMenus()){
//            NonVegMenuResponseDto responseDto=new NonVegMenuResponseDto();

//            responseDto.setNonVegFoodId(nonVegMenu.getId());
//            responseDto.setFoodItem(nonVegMenu.getFoodItem());
//            responseDto.setQuantity(nonVegMenu.getQuantity());
//            responseDto.setPrice(nonVegMenu.getPrice());
//            nonVegMenuResponseDtos.add(responseDto);
//        }
//        menuResponseDto.setNonVegMenusResponse(nonVegMenuResponseDtos);

//        List<VegMenuResponseDto>vegMenuResponseDtoList=new ArrayList<>();
//          vegMenuResponseDtoList.add(MenuMappers.fromVegMenuEntity(restaurants.getMenu()));
//        for(VegMenu vegMenu:restaurants.getMenu().getVegMenus()){
//            VegMenuResponseDto responseDto=new VegMenuResponseDto();
//            responseDto.setVegMenuId(vegMenu.getId());
//            responseDto.setFoodItem(vegMenu.getFoodItem());
//            responseDto.setQuantity(vegMenu.getQuantity());
//            responseDto.setPrice(vegMenu.getPrice());
//            vegMenuResponseDtoList.add(responseDto);
//        }
//        menuResponseDto.setVegMenusResponse(vegMenuResponseDtoList);
//        dto.setMenu(menuResponseDto);
        return dto;
    }
}
