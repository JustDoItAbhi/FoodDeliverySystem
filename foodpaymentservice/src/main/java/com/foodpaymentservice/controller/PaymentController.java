package com.foodpaymentservice.controller;

import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;
import com.foodpaymentservice.sendingtodelivery.DeliveryDTO;
import com.foodpaymentservice.service.GeneratePaymentLink;
import com.foodpaymentservice.service.orderservice.OrderService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
@Autowired
    private GeneratePaymentLink generatePaymentLink;
@Autowired
private OrderService orderService;

@PostMapping("/{email}")
    public ResponseEntity<PaymentResponseDTO> getPaymentLink(@PathVariable ("email")String email) throws StripeException {
    return ResponseEntity.ok(generatePaymentLink.getPaymentLink(email));
}
@GetMapping("/getCustomerAndGetOrder/{email}/{restName}")
public ResponseEntity<PaymentResponseDTO> getAll(@PathVariable ("email")String email,
                                                 @PathVariable ("restName")String restName)  {
    return ResponseEntity.ok(orderService.getCustomerAndOrder(email,restName));
}
    @GetMapping("/createDelivery/{email}")
    public ResponseEntity<DeliveryDTO> sendDtoDelivery(@PathVariable ("email")String email)  {
        return ResponseEntity.ok(orderService.createDelivery(email));
    }

}
