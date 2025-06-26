package com.restaurantservice.service.client;

import com.restaurantservice.dtos.responsedtos.menuResponse.NonVegMenuResponseDto;
import com.restaurantservice.dtos.responsedtos.RestaurantResponseDto;
import com.restaurantservice.entity.Restaurants;
import com.restaurantservice.exceptions.RestaurantNotExists;
import com.restaurantservice.mappers.RestaurantMapper;
import com.restaurantservice.repositories.AddressRepository;
import com.restaurantservice.repositories.NonVegMenuRepository;
import com.restaurantservice.repositories.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientRestaurantServiceImpl implements ClientRestaurantService{
    @Autowired
    @Qualifier("restaurantRedisTemplate")
    private RedisTemplate<String,RestaurantResponseDto>restaurantResponseDtoRedisTemplate;
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private NonVegMenuRepository nonVegMenuRepository;

    public ClientRestaurantServiceImpl(RedisTemplate<String, RestaurantResponseDto> restaurantResponseDtoRedisTemplate) {
        this.restaurantResponseDtoRedisTemplate = restaurantResponseDtoRedisTemplate;
    }

    @Override
    public RestaurantResponseDto getRestaurantByName(String name) {// added cart to redis
        Optional<Restaurants> savedRestaurants=restaurantsRepository.findByrestaurantName(name);
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant Not exists "+ name);
        }
        Restaurants rest= savedRestaurants.get();
        RestaurantResponseDto cachedRestaurant= restaurantResponseDtoRedisTemplate.opsForValue().get(name);
        if(cachedRestaurant!=null){
            return cachedRestaurant;
        }
        RestaurantResponseDto savedRest= RestaurantMapper.fromRestaurantEntity(rest);
        savedRest.setMenu(savedRest.getMenu());
        restaurantResponseDtoRedisTemplate.opsForValue().get(name);
        return savedRest;
    }
}
