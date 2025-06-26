package com.foodpaymentservice.service;

import com.foodpaymentservice.dtos.orderdtos.PaymentResponseDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;

public interface GeneratePaymentLink {
    PaymentResponseDTO getPaymentLink(String email)throws SecurityException, StripeException;
}
