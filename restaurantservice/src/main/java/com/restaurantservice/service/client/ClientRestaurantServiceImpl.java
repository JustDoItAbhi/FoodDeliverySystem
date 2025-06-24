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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientRestaurantServiceImpl implements ClientRestaurantService{
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private NonVegMenuRepository nonVegMenuRepository;
    @Autowired
    @Qualifier("restaurantRedisTemplate")
    private final RedisTemplate<String,RestaurantResponseDto>redisTemplate;

    public ClientRestaurantServiceImpl(RedisTemplate<String, RestaurantResponseDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public RestaurantResponseDto getRestaurantByName(String name) {
        Optional<Restaurants> savedRestaurants=restaurantsRepository.findByrestaurantName(name);
        if(savedRestaurants.isEmpty()){
            throw new RestaurantNotExists(" this restaurant Not exists "+ name);
        }
        Restaurants rest= savedRestaurants.get();
//        RestaurantResponseDto cachedRestaurant=redisTemplate.opsForValue().get(name);
//        if(cachedRestaurant!=null){
//            return cachedRestaurant;
//        }
        RestaurantResponseDto savedRest= RestaurantMapper.fromRestaurantEntity(rest);
        savedRest.setMenu(savedRest.getMenu());
        List<NonVegMenuResponseDto>nonVegMenuResponseDtos=new ArrayList<>();

//        redisTemplate.opsForValue().set(name,savedRest);
        return savedRest;
    }
}
