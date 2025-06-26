package com.foodpaymentservice.service;


import com.foodpaymentservice.dtos.orderdtos.OrderResponseDto;
import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;
import com.foodpaymentservice.httpscalls.HttpCallsFromService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements GeneratePaymentLink{
    @Value("${spring.data.stripe.secret}")
    private String apiKey;
    @Autowired
    private HttpCallsFromService callsFromService;
    @Autowired
    private RedisTemplate<String, PaymentResponseDTO> redisTemplate;


    public PaymentService(HttpCallsFromService callsFromService) {
        this.callsFromService = callsFromService;
    }

    public PaymentResponseDTO getPaymentLink(String email) throws StripeException {
        Stripe.apiKey=apiKey;
        PaymentResponseDTO dto=redisTemplate.opsForValue().get(email);
        if(dto==null){
            throw new RuntimeException("EMAIL NOT FOUND "+email);
        }
        ProductCreateParams productCreateParams= ProductCreateParams.builder()
                .setName(dto.getRestName())
                .build();
        Product product=Product.create(productCreateParams);
        System.out.println("Success! Here is your starter subscription product id: " + product.getId());
        PriceCreateParams priceCreateParams=PriceCreateParams.builder()
                .setCurrency("usd")
                .setProduct(product.getId())
                .setUnitAmount((long) dto.getTotalPrice()*10)
                .build();
        Price price=Price.create(priceCreateParams);
        System.out.println("Success! Here is your starter subscription price id: " + price.getId());

        PaymentLinkCreateParams link=PaymentLinkCreateParams.builder()
                .addLineItem(PaymentLinkCreateParams.LineItem.builder()
                        .setPrice(price.getId())
                        .setQuantity((long)dto.getTotalQuantity())
                        .build())
                .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                .setUrl("https://www.youtube.com/")
                                .build())
                        .build())
                .build();
        PaymentLink link1=PaymentLink.create(link);
        if(link1==null){
            throw new RuntimeException("LINK NOT FOUND");
        }
        dto.setPaymentlink(link1.getUrl());
        return dto;
    }
}
