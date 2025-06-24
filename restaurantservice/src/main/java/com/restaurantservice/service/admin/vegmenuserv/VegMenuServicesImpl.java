package com.restaurantservice.service.admin.vegmenuserv;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.menuRequest.VegMenuRequetDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.entity.Menu;
import com.restaurantservice.entity.Restaurants;
import com.restaurantservice.entity.VegMenu;
import com.restaurantservice.exceptions.MenuNullException;
import com.restaurantservice.exceptions.NonVegMenuAlreadyExists;
import com.restaurantservice.exceptions.RestaurantNotExists;
import com.restaurantservice.mappers.MenuMappers;
import com.restaurantservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VegMenuServicesImpl implements VegMenuServices{
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private VegMenuRepository vegMenuRepository;


    @Override
    public MenuResponseDto createVegMenu(MenuRequestDto dto) {
        Optional<Restaurants> savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ dto.getRestaurantName());
        }
        Restaurants rest=savedRestaurants.get();
        Menu menu = rest.getMenu();
        if (menu == null) {
            throw new MenuNullException("Menu must be created before adding veg items.");
        }
        List<VegMenu> vegList = menu.getVegMenus();
        if(vegList==null){
            vegList=new ArrayList<>();
        }
        for(VegMenuRequetDto vegDto : dto.getVegMenus()){
            Optional<VegMenu> savedMenu = vegMenuRepository.findByFoodItem(vegDto.getFoodItem());
            if(savedMenu.isPresent()){
                throw new NonVegMenuAlreadyExists("THIS FOOD "+ vegDto.getFoodItem()+" ALREADY EXISTS");
            }
            if(vegDto.getFoodItem().equals(vegDto.getFoodItem().toLowerCase())){
                vegDto.setFoodItem(vegDto.getFoodItem().toUpperCase());
            }
            VegMenu vegMenu = new VegMenu();
            vegMenu.setFoodItem(vegDto.getFoodItem());
            vegMenu.setQuantity(vegDto.getQuantity());
            vegMenu.setPrice(vegDto.getPrice());
            vegMenu.setSpicy(vegDto.getSpicy());
            vegMenuRepository.save(vegMenu);
            vegMenu.setMenu(menu);
            vegList.add(vegMenu);

        }
        menu.setVegMenus(vegList);
        menuRepository.save(menu);
        restaurantsRepository.save(rest);
        return MenuMappers.forVegOnly(menu);
    }
}

