package com.restaurantservice.service.admin.pizzamenuserv;

import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.menuRequest.PizzaRequestDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.entity.*;
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
public class PizzaMenuServiceImpl implements PizzaMenuService{
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PizzaRepository pizzaRepository;


    @Override
    public MenuResponseDto addPizza(MenuRequestDto dto) {
        Optional<Restaurants> savedRest = restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if (savedRest.isEmpty()) {
            throw new RestaurantNotExists("this restaurant not exists " + dto.getRestaurantName());
        }

        Restaurants rest = savedRest.get();
        Menu menu = rest.getMenu();
        List<Pizza> pizzas = menu.getPizzaList();
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }
        for (PizzaRequestDto pizzaRequestDto : dto.getPizzaRequestDtoList()) {
            Pizza pizza = new Pizza();
            if (pizzaRequestDto.getPizzaName().equals(pizzaRequestDto.getPizzaName().toLowerCase())) {
                pizzaRequestDto.setPizzaName(pizzaRequestDto.getPizzaName().toUpperCase());
            }
            Optional<Pizza> savedMenu = pizzaRepository.findByPizzaName(pizzaRequestDto.getPizzaName());
            if(savedMenu.isPresent()){
                throw new NonVegMenuAlreadyExists("THIS FOOD "+ pizzaRequestDto.getPizzaName()+" ALREADY EXISTS");
            }
            pizza.setPizzaName(pizzaRequestDto.getPizzaName());
            pizza.setPizzaSize(pizzaRequestDto.getPizzaSize());
            pizza.setQuantity(pizzaRequestDto.getQuantity());
            pizza.setPrice(pizzaRequestDto.getPrice());
            pizza.setSpicy(pizzaRequestDto.getSpicy());
            pizzas.add(pizza);
            pizza.setMenu(menu);
            pizzaRepository.save(pizza);
        }
        menu.setPizzaList(pizzas);
        rest.setMenu(menu);
        menuRepository.save(menu);
        restaurantsRepository.save(rest);

        return MenuMappers.forPizzaOnly(menu);
    }
}
