package com.restaurantservice.service.cartservice;

import com.restaurantservice.cart.cartdtos.CartResponseDto;
import com.restaurantservice.cart.cartdtos.Items;
import com.restaurantservice.cart.cartdtos.cartrequestdto.CreateCartRequestDto;
import com.restaurantservice.cart.cartdtos.cartrequestdto.ItemRequestDto;
import com.restaurantservice.entity.*;
import com.restaurantservice.entity.cart.Cart;
import com.restaurantservice.entity.cart.Item;
import com.restaurantservice.exceptions.FoodItemNotFound;
import com.restaurantservice.exceptions.RestaurantAlreadyExists;
import com.restaurantservice.mappers.CartMapper;
import com.restaurantservice.repositories.*;
import com.restaurantservice.repositories.cartrepositories.CartRepository;
import com.restaurantservice.repositories.cartrepositories.ItemRepository;
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
public class CartServiceImpl implements CartService {
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private NonVegMenuRepository nonVegMenuRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private VegMenuRepository vegMenuRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    @Qualifier("cartRedisTemplate")
    private RedisTemplate<String,CartResponseDto>redisTemplate;

    public CartServiceImpl(RedisTemplate<String, CartResponseDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public CartResponseDto createCart(CreateCartRequestDto dto) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        Optional<Restaurants> savedRestaurants = restaurantsRepository.findByrestaurantName(dto.getRestName());
        if (savedRestaurants.isEmpty()) {
            throw new RestaurantAlreadyExists(" this restaurant NOT exists " + dto.getRestName());
        }
        cartResponseDto.setRestName(savedRestaurants.get().getRestaurantName());
        cartResponseDto.setRestaurantId(savedRestaurants.get().getId());

        double totalQua = 0.0;
        double totalPrice = 0.0;
        List<Items> itemsList = new ArrayList<>();

        for (ItemRequestDto requestDto : dto.getItemRequestDtoList()) {
            Items items = new Items();
            items.setId(requestDto.getId());
            items.setFoodName(requestDto.getFoodName());
            items.setQuantity(requestDto.getQuantity());
            double itemPrice = 0.0;
            boolean found=false;
            Optional<NonVegMenu> nonVegMenu11 = nonVegMenuRepository.findByFoodItem(requestDto.getFoodName());
            if (nonVegMenu11.isPresent()) {
                itemPrice = nonVegMenu11.get().getPrice();
                found=true;
            }
            Optional<VegMenu> vegMenu11 = vegMenuRepository.findByFoodItem(requestDto.getFoodName());
            if (vegMenu11.isPresent()) {
                itemPrice = vegMenu11.get().getPrice();
                found=true;
            }

            Optional<Pizza> pizza1 = pizzaRepository.findByPizzaName(requestDto.getFoodName());
            if (pizza1.isPresent()) {
                itemPrice = pizza1.get().getPrice();
                found=true;
            }
            if(!found){
                throw new FoodItemNotFound("no such food exists "+requestDto.getFoodName());
            }
            totalQua+=items.getQuantity();
            totalPrice+=items.getQuantity()*itemPrice;
            itemsList.add(items);
//            cartResponseDto.setCreatetAt(LocalTime.now());
            cartResponseDto.setItemsList(itemsList);
            cartResponseDto.setTotalQuantity(totalQua);
            cartResponseDto.setTotalPrice(totalPrice);
        }
        Cart cart=new Cart();
        cart.setRestName(cartResponseDto.getRestName());
        cart.setRestaurantId(cartResponseDto.getRestaurantId());
        cart.setCreatedAt(LocalTime.now());
        List<Item>cartItems=new ArrayList<>();
        for(Items items:cartResponseDto.getItemsList()){
            Item item=new Item();
            item.setFoodName(items.getFoodName());
            item.setQuantity(items.getQuantity());
            cartItems.add(item);
            itemRepository.save(item);
        }
        cart.setItemsList(cartItems);
        cart.setTotalPrice(totalPrice);
        cart.setTotalQuantity(totalQua);
        cartRepository.save(cart);
        cartResponseDto.setCartId(cart.getId());
        redisTemplate.delete(dto.getRestName());
        redisTemplate.opsForValue().set(cartResponseDto.getRestName(), cartResponseDto, Duration.ofDays(12));
        return cartResponseDto;
    }

    @Override
    public CartResponseDto getFinalApi(String name) {
        Optional<Restaurants> savedRestaurants = restaurantsRepository.findByrestaurantName(name);
        if (savedRestaurants.isEmpty()) {
            throw new RestaurantAlreadyExists(" this restaurant NOT exists " + name);
        }

        return redisTemplate.opsForValue().get(name);
    }

    @Override
    public List<CartResponseDto> getAll() {
        List<Cart>cartList=cartRepository.findAll();
        List<CartResponseDto>responseDtoList=new ArrayList<>();
        for(Cart cart:cartList){
            responseDtoList.add(CartMapper.fromCartEntity(cart));
        }
        return responseDtoList;
    }

    @Override
    public String deleteCartById(long id) {
       cartRepository.deleteById(id);
        return "CART DELETED SUCCESSFULLY ID NUMBER :: "+id;
    }
//    @Override
//    public CartResponseDto getCartByRestaurant(String restName) {
//        Optional<Restaurants> savedRestaurants = restaurantsRepository.findByrestaurantName(restName);
//        if (savedRestaurants.isEmpty()) {
//            throw new RestaurantAlreadyExists(" this restaurant NOT exists " + restName);
//        }
//        Optional<Cart>cart=cartRepository.findByRestName(restName);
//        if(cart.isEmpty()){
//            throw new FoodItemNotFound("cart not found ");
//        }
////        CartResponseDto dto= CartMapper.fromCartEntity(cart.get());
//        CartResponseDto dto=(CartResponseDto) redisTemplate.opsForValue().get(restName);
//        return dto;
//    }

//    @Override
//    public CartResponseDto getCartByRestaurant(String restName) {
//        Optional<Restaurants> savedRestaurants = restaurantsRepository.findByrestaurantName(restName);
//        if (savedRestaurants.isEmpty()) {
//            throw new RestaurantAlreadyExists(" this restaurant NOT exists " + restName);
//        }
//        CartResponseDto cachedCart= (CartResponseDto) redisTemplate.opsForValue().get(restName);
//        if(cachedCart!=null){
//            return cachedCart;
//        }
//        return cachedCart;
//    }

}
