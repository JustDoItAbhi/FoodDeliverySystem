package com.restaurantservice.service.admin;

import com.restaurantservice.dtos.requestdtos.*;
import com.restaurantservice.dtos.responsedtos.NonVegMenuResponseDto;
import com.restaurantservice.dtos.responsedtos.VegMenuResponseDto;
import com.restaurantservice.entity.*;
import com.restaurantservice.exceptions.MenuNullException;
import com.restaurantservice.exceptions.NonVegMenuAlreadyExists;
import com.restaurantservice.mappers.MenuMappers;
import com.restaurantservice.mappers.RestaurantMapper;
import com.restaurantservice.dtos.responsedtos.MenuResponseDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import com.restaurantservice.exceptions.RestaurantAlreadyExists;
import com.restaurantservice.exceptions.RestaurantNotExists;
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

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if(savedRestaurants.isPresent()){
            throw new RestaurantAlreadyExists(" this restaurant already exists "+ dto.getRestaurantName());
        }
        Restaurants rest=new Restaurants();
        rest.setRestaurantName(dto.getRestaurantName());
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

    @Override
    public MenuResponseDto createNonVegMenu(MenuRequestDto dto) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ dto.getRestaurantName());
        }
        Restaurants rest=savedRestaurants.get();
        Menu menu=new Menu();
        menu.setRestName(dto.getRestaurantName());
        MenuResponseDto dto1= new MenuResponseDto();
        menu.setRestaurants(rest);
        rest.setMenu(menu);

// Save and link non-veg items
        List<NonVegMenu> nonVegList = menu.getNonVegMenus();
        if(nonVegList==null){
            nonVegList=new ArrayList<>();
        }
        for(NonVegMenuRequestDto nonVegDto : dto.getNonVegMenus()){
            Optional<NonVegMenu> savedMenu = nonVegMenuRepository.findByFoodItem(nonVegDto.getFoodItem());
            if(savedMenu.isPresent()){
                throw new NonVegMenuAlreadyExists("THIS FOOD "+ nonVegDto.getFoodItem()+" ALREADY EXISTS");
            }
            NonVegMenu nonVegMenu = new NonVegMenu();
            nonVegMenu.setFoodItem(nonVegDto.getFoodItem());
            nonVegMenu.setQuantity(nonVegDto.getQuantity());
            nonVegMenu.setPrice(nonVegDto.getPrice());
            nonVegMenu.setMenu(menu);  // associate with menu
            nonVegList.add(nonVegMenu);
        }
        menu.setNonVegMenus(nonVegList); // link to menu
        menuRepository.save(menu);  // Save menu along with items
        return MenuMappers.forMenuOnly(menu);

    }

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
            VegMenu vegMenu = new VegMenu();
            vegMenu.setFoodItem(vegDto.getFoodItem());
            vegMenu.setQuantity(vegDto.getQuantity());
            vegMenu.setPrice(vegDto.getPrice());
            vegMenu.setMenu(menu);
            vegList.add(vegMenu);
        }
        menu.setVegMenus(vegList);
        menuRepository.save(menu);
        return MenuMappers.forMenuOnly(menu);
    }

    @Override
    public RestaurantResponseDto getRestaurantByName(String name) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(name);
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ name);
        }
        Restaurants rest= savedRestaurants.get();
        return RestaurantMapper.fromRestaurantEntity(rest);

    }

    @Override
    public MenuResponseDto getById(long id) {
        Menu menu=menuRepository.findById(id).orElseThrow(
                ()->  new  MenuNullException("NO SUCH MENU "));

        return MenuMappers.forMenuOnly(menu);
    }


    private List<VegMenuResponseDto> fromVegEntity(List<VegMenuRequetDto>vegMenuRequetDtoList){
        List<VegMenu>vegMenuList=new ArrayList<>();
        for(VegMenuRequetDto vegMenu:vegMenuRequetDtoList){
            VegMenu vegMenuResponseDto=new VegMenu();
            vegMenuResponseDto.setFoodItem(vegMenu.getFoodItem());
            vegMenuResponseDto.setQuantity(vegMenu.getQuantity());
            vegMenuResponseDto.setPrice(vegMenu.getPrice());
            vegMenuList.add(vegMenuResponseDto);
            vegMenuRepository.save(vegMenuResponseDto);
        }
        return MenuMappers.fromVegMenuEntity(vegMenuList);
    }

//    @Override
//    public MenuResponseDto createVegMenu(MenuRequestDto dto) {
//        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(dto.getRestaurantName());
//        if(savedRestaurants.isEmpty()){
//            throw new RestaurantNotExists(" this restaurant not exists "+ dto.getRestaurantName());
//        }
//        Restaurants rest=savedRestaurants.get();
//        Menu menu=savedRestaurants.get().getMenu();
//        menu.setRestName(dto.getRestaurantName());
//        List<VegMenu>vegList=new ArrayList<>();
//        for(VegMenuRequetDto vegMenuRequestDto: dto.getVegMenus()){
//            Optional<VegMenu> savedMenu=vegMenuRepository.findByFoodItem(vegMenuRequestDto.getFoodItem());
//            if(savedMenu.isPresent()){
//                throw new NonVegMenuAlreadyExists("THIS FOOD "+ vegMenuRequestDto.getFoodItem()+" ALREADY EXISTS");
//            }
//            VegMenu vegMenu=new VegMenu();
//            vegMenu.setFoodItem(vegMenuRequestDto.getFoodItem());
//            vegMenu.setQuantity(vegMenuRequestDto.getQuantity());
//            vegMenu.setPrice(vegMenuRequestDto.getPrice());
//            vegList.add(vegMenu);
//            vegMenuRepository.save(vegMenu);
//        }
//        menu.setVegMenus(vegList);
//        menu.setRestaurants(rest);
//        rest.setMenu(menu);
//        menuRepository.save(menu);
//        restaurantsRepository.save(rest);
//        return MenuMappers.fromVegMenuEntity(menu);
//
//    }
}
