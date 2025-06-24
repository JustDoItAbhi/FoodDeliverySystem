package com.restaurantservice.service.admin;

import com.restaurantservice.dtos.requestdtos.*;
import com.restaurantservice.dtos.requestdtos.menuRequest.MenuRequestDto;
import com.restaurantservice.dtos.requestdtos.menuRequest.NonVegMenuRequestDto;
import com.restaurantservice.dtos.requestdtos.menuRequest.PizzaRequestDto;
import com.restaurantservice.dtos.requestdtos.menuRequest.VegMenuRequetDto;
import com.restaurantservice.dtos.responsedtos.menuResponse.VegMenuResponseDto;
import com.restaurantservice.entity.*;
import com.restaurantservice.exceptions.MenuNullException;
import com.restaurantservice.exceptions.NonVegMenuAlreadyExists;
import com.restaurantservice.mappers.MenuMappers;
import com.restaurantservice.mappers.PizzaMapper;
import com.restaurantservice.mappers.RestaurantMapper;
import com.restaurantservice.dtos.responsedtos.menuResponse.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import com.restaurantservice.exceptions.RestaurantAlreadyExists;
import com.restaurantservice.exceptions.RestaurantNotExists;
import com.restaurantservice.mappers.VegMenuMapper;
import com.restaurantservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminResaturantServiceImpl implements AdminRestaurantsServices {
    @Autowired
   private RestaurantsRepository restaurantsRepository;
    @Autowired
   private AddressRepository addressRepository;
    @Autowired
    private NonVegMenuRepository nonVegMenuRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private VegMenuRepository vegMenuRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    // ADDED RESTAURANT
    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if(savedRestaurants.isPresent()){
            throw new RestaurantAlreadyExists(" this restaurant already exists "+ dto.getRestaurantName());
        }

        Restaurants rest=new Restaurants();
        rest.setRestaurantName(dto.getRestaurantName().toUpperCase());
        rest.setCreatedAt(LocalTime.now());
        List<Addresses>addresseList=new ArrayList<>();
        for(AddressesRequestDto requestDto:dto.getAddress()) {
            Addresses addresses=new Addresses();
            addresses.setCreatedAt(LocalTime.now());
            addresses.setBuildingNumber(requestDto.getBuildingNumber());
            addresses.setStreet(requestDto.getStreet());
            addresses.setPinCode(requestDto.getPinCode());
            addresses.setCity(requestDto.getCity());
            addresses.setCountry(requestDto.getCountry());
            addresses.setRestaurants(rest);
            addresseList.add(addresses);
        }
        rest.setAddress(addresseList);
        restaurantsRepository.save(rest);
        return RestaurantMapper.forCreatingRest(rest);
    }

    //ADDED NON VEG MENU
    @Override
    public MenuResponseDto createNonVegMenu(MenuRequestDto dto) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ dto.getRestaurantName());
        }
        Restaurants rest=savedRestaurants.get();
        Menu menu= new Menu();
//        if (menu == null) {
//            throw new MenuNullException("Menu must be created before adding veg items." + dto.getNonVegMenus().toString());
////            menu=menuRepository.save(menu);
////            restaurantsRepository.save(rest);
//        }
//        menu = new Menu();

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

    //ADDED VEG MENU
    @Override
    public MenuResponseDto createVegMenu(MenuRequestDto dto) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
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

    //ADDED PIZZA
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
                pizza.setPizzaName(pizzaRequestDto.getPizzaName());
                pizza.setPizzaSize(pizzaRequestDto.getPizzaSize());
                pizza.setQuantity(pizzaRequestDto.getQuantity());
                pizza.setPrice(pizzaRequestDto.getPrice());
                pizza.setSpicy(pizzaRequestDto.getSpicy());
                pizzas.add(pizza);
            pizzaRepository.save(pizza);
        }
        menu.setPizzaList(pizzas);
            rest.setMenu(menu);
            menuRepository.save(menu);
            restaurantsRepository.save(rest);

        return MenuMappers.forPizzaOnly(menu);
    }
    //GET RESTAURANT BY NAME
    @Override
    public RestaurantResponseDto getRestaurantByName(String name) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(name);
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ name);
        }
        Restaurants rest= savedRestaurants.get();
        return RestaurantMapper.fromRestaurantEntity(rest);

    }

    //GET MENU BY ID
    @Override
    public MenuResponseDto getById(long id) {
        Menu menu=menuRepository.findById(id).orElseThrow(
                ()->  new  MenuNullException("NO SUCH MENU "));

        return MenuMappers.fullMenuMapper(menu);
    }




//    private List<VegMenuResponseDto> fromVegEntity(List<VegMenuRequetDto>vegMenuRequetDtoList){
//        List<VegMenu>vegMenuList=new ArrayList<>();
//        for(VegMenuRequetDto vegMenu:vegMenuRequetDtoList){
//            VegMenu vegMenuResponseDto=new VegMenu();
//            vegMenuResponseDto.setFoodItem(vegMenu.getFoodItem());
//            vegMenuResponseDto.setQuantity(vegMenu.getQuantity());
//            vegMenuResponseDto.setPrice(vegMenu.getPrice());
//            vegMenuList.add(vegMenuResponseDto);
//            vegMenuRepository.save(vegMenuResponseDto);
//        }
//        return VegMenuMapper.fromVegMenuEntity(vegMenuList);
//    }


}
