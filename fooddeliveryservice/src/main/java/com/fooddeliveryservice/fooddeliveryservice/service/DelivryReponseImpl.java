package com.fooddeliveryservice.fooddeliveryservice.service;

import com.fooddeliveryservice.fooddeliveryservice.repositories.DeliveryRepository;
import com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery.DeliveryDTO;
import com.fooddeliveryservice.fooddeliveryservice.dtos.sendingtodelivery.DeliveryResponse;
import com.fooddeliveryservice.fooddeliveryservice.entity.DeliveringOrder;
import com.fooddeliveryservice.fooddeliveryservice.entity.DeliveryStatus;
import com.fooddeliveryservice.fooddeliveryservice.resttemplates.RestClinetCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class DelivryReponseImpl implements DeliveryService{
@Autowired
    private DeliveryRepository deliveryRepository;
@Autowired
private RestClinetCalls clinetCalls;

    @Override
    public DeliveryResponse startCooking(String email) {
        Optional<DeliveringOrder> exsistingOrder = deliveryRepository.findByEmail(email);
        DeliveryResponse response = new DeliveryResponse();
        if (exsistingOrder.isEmpty()) {
            DeliveryDTO dto = clinetCalls.responseEntity(email);
            if (dto == null) {
                throw new RuntimeException("DELIVERY FAILED " + email);
            }

            DeliveringOrder order = new DeliveringOrder();
            order.setEmail(dto.getEmail());
            order.setCartId(dto.getCartId());
            order.setCustomerName(dto.getCustomerName());
            order.setDeliveryStatus(DeliveryStatus.FOOD_PROCCESSING);
            order.setRestaurantId(dto.getRestaurantId());
            order.setRestName(dto.getRestName());
            order.setTotalQuantity(dto.getTotalQuantity());
            order.setTotalPrice(dto.getTotalPrice());
            deliveryRepository.save(order);
            response.setCurrentTime(LocalTime.now());
            response.setDeliveryStatus(order.getDeliveryStatus());
            response.setEmail(order.getEmail());
        } else {
            DeliveryStatus status =exsistingOrder.get().getDeliveryStatus();
            switch (status){
                case FOOD_PROCCESSING -> {
                    exsistingOrder.get().setDeliveryStatus(DeliveryStatus.ORDER_READY_TO_DELIVER);
                  deliveryRepository.save(exsistingOrder.get());
                  response.setEmail(email);
                    response.setCurrentTime(LocalTime.now());
                    response.setDeliveryStatus(DeliveryStatus.ORDER_READY_TO_DELIVER);
                }
                case ORDER_READY_TO_DELIVER -> {
                    exsistingOrder.get().setDeliveryStatus(DeliveryStatus.DELIVERED);
                    deliveryRepository.save(exsistingOrder.get());
                    response.setEmail(email);
                    response.setCurrentTime(LocalTime.now());
                    response.setDeliveryStatus(DeliveryStatus.DELIVERED);
                }
                case DELIVERED -> {
                    response.setEmail(email);
                    response.setCurrentTime(LocalTime.now());
                    response.setDeliveryStatus(DeliveryStatus.THANK_YOU_FOR_CHOOSING_US_LEAVE_TIP);
                }
        }
    }
        return response;
    }


}
//Duration duration = Duration.between(order.getUpdatedTime(), LocalDateTime.now());
//
//    if (duration.toMinutes() < 5) {
//long remaining = 5 - duration.toMinutes();
//        response.setDeliveryStatus(order.getDeliveryStatus());
//        response.setMessage("Please wait " + remaining + " more minute(s) for next status update.");
//        return response;
//    }
//
//            // Time passed → move to next status
//            switch (order.getDeliveryStatus()) {
//        case FOOD_PROCCESSING -> {
//        order.setDeliveryStatus(DeliveryStatus.ORDER_READY_TO_DELIVER);
//        }
//                case ORDER_READY_TO_DELIVER -> {
//        order.setDeliveryStatus(DeliveryStatus.DELIVERED);
//        }
//                case DELIVERED -> {
//        response.setDeliveryStatus(DeliveryStatus.DELIVERED);
//            response.setMessage("Order already delivered.");
//            return response;
//        }
//                }
//
//                order.setUpdatedTime(LocalDateTime.now());
//        deliveryRepository.save(order);
//    response.setDeliveryStatus(order.getDeliveryStatus());
//        return response;
//}