package com.restaurantservice.service.admin.nonvegmenuservice;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.menuRequest.NonVegMenuRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.entity.Menu;
import com.restaurantservice.entity.NonVegMenu;
import com.restaurantservice.entity.Restaurants;
import com.restaurantservice.exceptions.NonVegMenuAlreadyExists;
import com.restaurantservice.exceptions.RestaurantNotExists;
import com.restaurantservice.mappers.MenuMappers;
import com.restaurantservice.repositories.MenuRepository;
import com.restaurantservice.repositories.NonVegMenuRepository;
import com.restaurantservice.repositories.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NonVegetericanMenuServiceImpl implements NonVegetericanMenuService{
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private NonVegMenuRepository nonVegMenuRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuResponseDto createNonVegMenu(MenuRequestDto dto) {
        Optional<Restaurants> savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ dto.getRestaurantName());
        }
        Restaurants rest=savedRestaurants.get();
        Menu menu= new Menu();

        List<NonVegMenu> nonVegList = menu.getNonVegMenus();
        if(nonVegList==null){
            nonVegList=new ArrayList<>();
        }
        for(NonVegMenuRequestDto nonVegDto : dto.getNonVegMenus()){
            Optional<NonVegMenu> savedMenu = nonVegMenuRepository.findByFoodItem(nonVegDto.getFoodItem());
            if(savedMenu.isPresent()){
                throw new NonVegMenuAlreadyExists("THIS FOOD "+ nonVegDto.getFoodItem()+" ALREADY EXISTS");
            }
            if(nonVegDto.getFoodItem().equals(nonVegDto.getFoodItem().toLowerCase())){
                nonVegDto.setFoodItem(nonVegDto.getFoodItem().toUpperCase());
            }
            NonVegMenu nonVegMenu = new NonVegMenu();
            nonVegMenu.setFoodItem(nonVegDto.getFoodItem());
            nonVegMenu.setQuantity(nonVegDto.getQuantity());
            nonVegMenu.setPrice(nonVegDto.getPrice());
            nonVegMenu.setSpicy(nonVegDto.getSpicy());
            nonVegMenuRepository.save(nonVegMenu);
            nonVegList.add(nonVegMenu);
            nonVegMenu.setMenu(menu);
        }
        menu.setRestName(dto.getRestaurantName());
        menu.setNonVegMenus(nonVegList);
        menuRepository.save(menu);
        rest.setMenu(menu);
        menu.setRestaurants(rest);
        restaurantsRepository.save(rest);
        return MenuMappers.forNonVegOnly(menu);
    }
}
