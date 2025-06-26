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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
    @Autowired
    @Qualifier("restaurantRedisTemplate")
    private RedisTemplate<String,RestaurantResponseDto>redisTemplate;

    public AdminResaturantServiceImpl(RedisTemplate<String, RestaurantResponseDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

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

    //GET RESTAURANT BY NAME AND ADDED TO REDIS CACHE
    @Override
    public RestaurantResponseDto getRestaurantByName(String name) {
        Optional<Restaurants>savedRestaurants=restaurantsRepository.findByrestaurantName(name);
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant not exists "+ name);
        }
        Restaurants rest= savedRestaurants.get();

        RestaurantResponseDto responseDto=RestaurantMapper.fromRestaurantEntity(rest);
                redisTemplate.opsForValue().set(name,responseDto, Duration.ofDays(12));
        return responseDto;

    }
    //GET MENU BY ID
    @Override
    public MenuResponseDto getById(long id) {
        Menu menu=menuRepository.findById(id).orElseThrow(
                ()->  new  MenuNullException("NO SUCH MENU "));

        return MenuMappers.fullMenuMapper(menu);
    }
}
