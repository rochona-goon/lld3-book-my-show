package com.scaler.bookMyshow.Controllers;

import com.scaler.bookMyshow.DTOs.Payment.PaymentResponseDto;
import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.Payment;
import com.scaler.bookMyshow.Services.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponseDto makePayment(@RequestParam int bookingId){
        PaymentResponseDto responseDto = new PaymentResponseDto();
        try{
            Payment payment = paymentService.makePayment(bookingId);
            responseDto.setPaymentAmount(payment.getAmount());
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Payment has been processed");

        } catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("Error occurred during payment processing");
        }
        return responseDto;
    }
}
